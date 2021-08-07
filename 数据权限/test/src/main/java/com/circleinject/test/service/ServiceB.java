package com.circleinject.test.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired,@Lazy}))
public class ServiceB extends TopService{
    
    final ServiceA serviceA;
    
    public void a() {
        System.out.println("b");
    }
    
}
