package com.circleinject.test.service;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.circleinject.test.Entity.SysRole;
import com.circleinject.test.mapper.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyBatisPlusService {

  @Autowired ServiceA serviceA;
  @Autowired SysRoleMapper sysRoleMapper;

  public void test() {
    LambdaQueryChainWrapper<SysRole> eq = serviceA.lambdaQuery().eq(SysRole::getId, "9").orderByAsc(SysRole::getName);
    List<SysRole> list = eq.list();
    System.out.println(list);
    SysRole sysRole = sysRoleMapper.findByUserId(9L);
    System.out.println(sysRole);
  }
}
