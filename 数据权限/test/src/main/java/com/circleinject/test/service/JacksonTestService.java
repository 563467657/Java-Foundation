package com.circleinject.test.service;

import com.circleinject.test.serializer.BitSetDeserializer;
import com.circleinject.test.serializer.BitSetSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.google.common.collect.ImmutableBiMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;
import java.util.BitSet;

@Service
public class JacksonTestService {

  @Autowired ObjectMapper objectMapper;

  public void test() throws JsonProcessingException {
    ImmutableBiMap<Object, Object> map =
        ImmutableBiMap.builder().put(1, "aaa").put(2, "bbb").put(3, "ccc").build();
    System.out.println(map);
    objectMapper.registerModule(new GuavaModule());
    String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
    System.out.println(s);
    ImmutableBiMap<Integer, String> hashBiMap = objectMapper.readValue(s, ImmutableBiMap.class);
    System.out.println(hashBiMap);
  }

  public void testBitSet() throws IOException {
    BitSet bitSet = new BitSet();
    bitSet.set(0);
    bitSet.set(1);
    bitSet.set(2);
    bitSet.set(3);
    bitSet.set(10);
    bitSet.set(700);
    SimpleModule simpleModule = new SimpleModule();
    simpleModule.addSerializer(BitSet.class, new BitSetSerializer());
    simpleModule.addDeserializer(BitSet.class, new BitSetDeserializer());
    objectMapper.registerModule(simpleModule);

    String s = objectMapper.writeValueAsString(bitSet);
    System.out.println(s);
    BitSet bitSet1 = objectMapper.readValue(s, BitSet.class);
    System.out.println(bitSet1);

    String s1 = Base64.getEncoder().encodeToString(bitSet.toByteArray());
    System.out.println(s1);
    byte[] decode = Base64.getDecoder().decode(s1);
    BitSet bitSet2 = BitSet.valueOf(decode);
    System.out.println(bitSet2);
  }
}
