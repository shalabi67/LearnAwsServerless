package com.serverless.framework.dynamodb.repository;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class DynamodbAttributes {
    private Map<String, AttributeValue> attributesMap;

    public DynamodbAttributes() {
        attributesMap = new HashMap<>();
    }
    public DynamodbAttributes(Map<String, AttributeValue> attributesMap) {
        this.attributesMap = attributesMap;
    }

    public Map<String, AttributeValue> getAttributesMap() {
        return attributesMap;
    }

    public String getString(String key) {
        return get(key, AttributeValue::s);
    }
    public void putString(String key, String value) {
        attributesMap.put(key, AttributeValue.builder().s(value).build());
    }

    private <T> T get(String key, Function<AttributeValue, T> function) {
        AttributeValue value = attributesMap.get(key);
        if(value != null) {
            return function.apply(value);
        }

        return null;
    }

}
