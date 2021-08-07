package com.example.jpatest.service;

import com.example.jpatest.entity.SysUserEntity;
import com.example.jpatest.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

  @Autowired SysUserRepository sysUserRepository;

  public void test() {
    List<SysUserEntity> all = sysUserRepository.findAll();
    System.out.println(all);
  }
}
