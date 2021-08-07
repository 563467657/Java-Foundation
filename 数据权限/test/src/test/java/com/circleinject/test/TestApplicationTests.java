package com.circleinject.test;

import com.circleinject.test.service.JacksonTestService;
import com.circleinject.test.service.MyBatisPlusService;
import com.circleinject.test.service.ServiceA;
import com.circleinject.test.service.ServiceB;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class TestApplicationTests {
  @Autowired private ServiceA serviceA;
  @Autowired private JacksonTestService jacksonTestService;
  @Autowired MyBatisPlusService mybatisPlusService;

  @Test
  void contextLoads() {
    serviceA.methodA();
  }

  @Test
  void test() throws JsonProcessingException {
    jacksonTestService.test();
  }

  @Test
  void testBitSet() throws IOException {
    jacksonTestService.testBitSet();
  }

  @Test
  void testMyBatisPlus() {
    mybatisPlusService.test();
  }
}
