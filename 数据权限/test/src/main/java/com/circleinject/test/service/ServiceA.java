package com.circleinject.test.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.circleinject.test.Entity.SysRole;
import com.circleinject.test.mapper.SysRoleMapper;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServiceA extends ServiceImpl<SysRoleMapper, SysRole> {
    
    public void a() {
        System.out.println("a");
    }
    
    @Transactional(rollbackFor = Exception.class)
    public void methodA() {
        SysRole sysRole = new SysRole();
        sysRole.setId(999L);
        sysRole.setName("999");
        super.save(sysRole);
        this.methodB();
    }
    
    public void methodB() {
        SysRole sysRole = new SysRole();
        sysRole.setId(1000L);
        sysRole.setName("1000");
        super.save(sysRole);
    }

}
