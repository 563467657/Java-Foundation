package com.example.jpatest.handler;

import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.util.TablesNamesFinder;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;

@Slf4j
public class PermissionHandler {

  public String doFilter(String sql) throws JSQLParserException {
    log.info("进入了PermissionHandler");
    Statement statement = CCJSqlParserUtil.parse(sql);
    // 先判断当前查询的sql中的所有表，是否存在于业务对象中，如果存在则拦截，不存在则直接返回
    TablesNamesFinder tableNameFinder = new TablesNamesFinder();
    List<String> tableList = tableNameFinder.getTableList(statement);
    // 获取当前角色的所有业务对象
    List<String> bizObjList = this.getBizObjList();
    Collection<String> intersection = CollectionUtils.intersection(tableList, bizObjList);
    if (intersection.isEmpty()) {
      return sql;
    }

    if (statement instanceof Select) {
      // 解析select中的所有表
      Select select = (Select) statement;
      SelectBody selectBody = select.getSelectBody();
      Map<String, String> tableNameAliasMap = new HashMap<>();
      if (selectBody instanceof PlainSelect) {
        // 多表联查
        PlainSelect plainSelect = (PlainSelect) selectBody;
        log.info("plainSelect" + plainSelect);

        // 查询表中的第一张表
        FromItem fromItem = plainSelect.getFromItem();
        List<Join> joins = plainSelect.getJoins();
        if (fromItem instanceof Table) {
          // from中的第一个值是一张表
          Table table = (Table) fromItem;
          if (joins == null) {
            // 为空说明是单表查询

            // 单表查询的情况下需要根据业务对象来判断是否需要加alias条件，如果业务对象是单表业务对象则不需要拼接别名，否则需要拼接
            // 先判断业务对象是不是单表

            // TODO 查询当前表对应的业务对象及其子对象，应该写一个查询方法，也可以写在getBizObjList中,此处暂时使用bizObjList对象代替
            if (CollectionUtils.isEmpty(bizObjList)) {

            } else if (bizObjList.size() == 1) {
              // 说明是单表，单表的情况不需要处理

            } else if (bizObjList.size() > 1) {
              // 说明是多表，多表的情况需要处理
              // 处理的时候需要判断原sql是否有别名，如果有别名不做处理，如果没有别名，则做处理
              Alias alias = table.getAlias();
              if (alias != null) {
                // 原本有别名 不需要处理
                // 储存表名和别名的对应关系，用于拼接wheresql时拼接别名
                tableNameAliasMap.put(table.getName(), alias.getName());
              } else {
                // 原本没有别名的情况，直接将selectColumn和whereExpression修改为表名.字段名
                
                //----------------- 处理selectColumn  begin---------------------
                List<SelectItem> selectItems = plainSelect.getSelectItems();

                // 创建一个新的selectItem并覆盖原本的selectItem
                List<SelectItem> newSelectItems = new ArrayList<>();
                for (SelectItem selectItem : selectItems) {
                  if (selectItem instanceof AllColumns) {
                    AllColumns allColumns = (AllColumns) selectItem;
                    //需要转换成AllTableColumns
                    AllTableColumns allTableColumns = new AllTableColumns();
                    allTableColumns.setTable(table);
                    allTableColumns.setASTNode(allColumns.getASTNode());
                    newSelectItems.add(allTableColumns);
                  } else if (selectItem instanceof AllTableColumns) {
                    //已经是表名.*   不需要处理
                    newSelectItems.add(selectItem);
                  } else if (selectItem instanceof SelectExpressionItem) {
                    // 查询条件为具体行
                    SelectExpressionItem selectExpressionItem = (SelectExpressionItem) selectItem;
                    
                  }
                }
                plainSelect.setSelectItems(newSelectItems);
                log.info(plainSelect.toString());
                //----------------- 处理selectColumn  end---------------------
                
                //----------------- 处理whereExpression  begin---------------------
                
                //----------------- 处理whereExpression  end---------------------
              }
            }

          } else {
            // 不为空说明是多表查询
            // 多表查询的情况下原有sql应该已经加了别名或者根据表名.列名来查询对应数据
            // 如果原本没加，这里我们也不知道具体字段是哪张表上的字段
            // 这里需要业务系统是多表查询的情况下给原有的sql指定表名.列名或者别名.列名的条件
          }

        } else if (fromItem instanceof SubJoin) {
          // TODO 连接查询
        } else if (fromItem instanceof SubSelect) {
          // TODO 子查询
        }
        // 后续连接表,每一个join对应一个fromItem和对应的连接方式

      } else if (selectBody instanceof WithItem) {
        // WithItem对应SQL语句的with关键字，并不多见，暂不处理
        WithItem withItem = (WithItem) selectBody;
        log.info("withItem" + withItem);
      }
      System.out.println("TableJoinFilter处理后的sql:" + sql);

    } else {
      return sql;
    }
    return sql;
  }

  /**
   * 获取当前角色对应的所有业务对象表的集合
   *
   * @return 表名集合，此处可以考虑返回一个map以承接更多信息
   */
  public List<String> getBizObjList() {
    List<String> list = new ArrayList<>();
    list.add("sys_user");
    list.add("sys_role");
    return list;
  }
}
