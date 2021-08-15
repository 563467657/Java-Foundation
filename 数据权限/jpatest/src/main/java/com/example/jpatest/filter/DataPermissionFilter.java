package com.example.jpatest.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.sf.jsqlparser.JSQLParserException;

@Data
@AllArgsConstructor
public abstract class DataPermissionFilter {
  
  private DataPermissionFilter nextPermissionFilter;

  public abstract String doFilter(String sql) throws JSQLParserException;
}
