package handler;

import com.baomidou.mybatisplus.extension.plugins.handler.DataPermissionHandler;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.ItemsList;
import net.sf.jsqlparser.schema.Column;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomizeDataPermissionHandler implements DataPermissionHandler {
  @Override
  public Expression getSqlSegment(Expression where, String mappedStatementId) {
    System.out.println(where);
    System.out.println(mappedStatementId);
    // 1. 获取权限过滤相关信息

    Set<String> set = new HashSet<>();
    set.add("超级管理员");
    set.add("普通管理员");
    ItemsList itemsList =
        new ExpressionList(set.stream().map(StringValue::new).collect(Collectors.toList()));
    InExpression inExpression = new InExpression(new Column("name"), itemsList);
    if (where == null) {
      return inExpression;
    }
    return new AndExpression(where, inExpression);
  }
}
