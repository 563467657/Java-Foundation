package com.example.jpatest.filter.impl;

import com.example.jpatest.filter.DataPermissionFilter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ColumnFilter  extends DataPermissionFilter {
  
  public ColumnFilter(DataPermissionFilter nextPermissionFilter) {
    super(nextPermissionFilter);
  }
  
  @Override
  public String doFilter(String sql) {
    log.info("进入了ColumnFilter");
    return sql;
  }
  
  
}
