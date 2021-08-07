package com.circleinject.test.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.BitSet;

public class BitSetDeserializer extends JsonDeserializer<BitSet> {
    @Override
    public BitSet deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        byte[] bytes = p.getBinaryValue();
        return BitSet.valueOf(bytes);
    }
}
