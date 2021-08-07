package com.example.jpatest.interceptor;

import org.hibernate.resource.jdbc.spi.StatementInspector;

public class DataPermissionInterceptor implements StatementInspector {
  @Override
  public String inspect(String sql) {
    //解析sql并拼装权限
    System.out.println(sql);
    return null;
  }
}
