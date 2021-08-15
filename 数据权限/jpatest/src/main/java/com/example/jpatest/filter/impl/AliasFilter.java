package com.example.jpatest.filter.impl;

import com.example.jpatest.filter.DataPermissionFilter;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.util.TablesNamesFinder;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 处理原有sql的别名，包括查询表，查询字段，查询条件
 */
@Slf4j
public class AliasFilter extends DataPermissionFilter {
  
  public AliasFilter(DataPermissionFilter nextPermissionFilter) {
    super(nextPermissionFilter);
  }
  
  @Override
  public String doFilter(String sql) throws JSQLParserException {
    log.info("进入了AliasFilter");
    Statement statement = CCJSqlParserUtil.parse(sql);
    //先判断当前查询的sql中的所有表，是否存在于业务对象中，如果存在则拦截，不存在则直接返回
    TablesNamesFinder tableNameFinder = new TablesNamesFinder();
    List<String> tableList = tableNameFinder.getTableList(statement);
    //获取当前角色的所有业务对象
    List<String> bizObjList = this.getBizObjList();
    Collection<String> intersection = CollectionUtils.intersection(tableList, bizObjList);
    if (intersection.isEmpty()) {
      return sql;
    }

    if (statement instanceof Select) {
      //解析select中的所有表
      Select select = (Select) statement;
      SelectBody selectBody = select.getSelectBody();
      if (selectBody instanceof PlainSelect) {
        //多表联查
        PlainSelect plainSelect = (PlainSelect) selectBody;
        log.info("plainSelect" + plainSelect);
        
        //查询表中的第一张表
        FromItem fromItem = plainSelect.getFromItem();
        if (fromItem instanceof Table) {
          //单表查询，需要根据业务对象判断需要连接几个表
        }else if (fromItem instanceof SubJoin) {
          //TODO 连接查询
        }else if (fromItem instanceof SubSelect) {
          //TODO 子查询
        }
        //后续连接表,每一个join对应一个fromItem和对应的连接方式
        List<Join> joins = plainSelect.getJoins();
  
      } else if (selectBody instanceof WithItem) {
        //WithItem对应SQL语句的with关键字，并不多见，暂不处理
        WithItem withItem = (WithItem) selectBody;
        log.info("withItem" + withItem);
      }
      System.out.println("TableJoinFilter处理后的sql:" + sql);
      return this.getNextPermissionFilter().doFilter(sql);
    } else {
      return sql;
    }
  }
  
  /**
   * 获取当前角色对应的所有业务对象表的集合
   * @return 表名集合，此处可以考虑返回一个map以承接更多信息
   */
  public List<String> getBizObjList() {
    List<String> list = new ArrayList<>();
    list.add("sys_user");
    return list;
  }
  
}
