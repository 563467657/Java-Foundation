package com.example.jpatest.interceptor;

import com.example.jpatest.filter.DataPermissionFilter;
import com.example.jpatest.filter.impl.ColumnFilter;
import com.example.jpatest.filter.impl.TableJoinFilter;
import com.example.jpatest.handler.PermissionHandler;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import org.hibernate.resource.jdbc.spi.StatementInspector;

@Slf4j
public class DataPermissionInterceptor implements StatementInspector {
  @Override
  public String inspect(String sql) {
    //解析sql并拼装权限
    System.out.println(sql);
    //最简单的单表sql解析，添加权限sql
    sql = "select sys_user.id from sys_user where id = 1";
//    sql = "select t.id from sys_user t,sys_role r where id = 1";
//    sql = "select t.id from sys_user t left join sys_role r on t.id = r.roleId where id = 1";
    //sql = "select * from (select * from sys_user where id = 1) t";

    //先处理别名，再连表，最后再拼装条件
//    DataPermissionFilter columnFilter = new ColumnFilter(null);
//    DataPermissionFilter tableFilter = new TableJoinFilter(columnFilter);
//    DataPermissionFilter aliasFilter = new TableJoinFilter(tableFilter);
    PermissionHandler permissionHandler = new PermissionHandler();
    try {
//      sql = tableFilter.doFilter(sql);
      sql = permissionHandler.doFilter(sql);
      System.out.println(sql);
    } catch (JSQLParserException e) {
      log.error("sql解析失败", e);
      return sql;
    }
    return sql;
  }
}
