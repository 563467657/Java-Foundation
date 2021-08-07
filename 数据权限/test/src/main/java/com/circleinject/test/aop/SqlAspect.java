package com.circleinject.test.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class SqlAspect {

  @Pointcut("execution(public * org.apache.ibatis.scripting.xmltags.DynamicContext.getBindings(..))")
//  @Pointcut("execution(public * com.circleinject.test.service.MyBatisPlusService.test(..))")
  public void sqlAspect() {}
  
  @Around("sqlAspect()")
  public Object around(ProceedingJoinPoint point) throws Throwable {
    log.info("执行前");
    Object proceed = point.proceed();
    log.info("执行后" + proceed);
    return proceed;
  }
  
}
