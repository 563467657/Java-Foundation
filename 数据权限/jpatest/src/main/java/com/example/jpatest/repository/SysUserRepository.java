package com.example.jpatest.repository;

import com.example.jpatest.entity.SysUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysUserRepository extends JpaRepository<SysUserEntity, Long> {}
