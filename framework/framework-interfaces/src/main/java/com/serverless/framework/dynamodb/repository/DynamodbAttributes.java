package com.serverless.framework.dynamodb.repository;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
        if (value == null || key == null) {
            return;
        }
        attributesMap.put(key, AttributeValue.builder().s(value).build());
    }

    public Long getLong(String key) {
        String value = get(key, AttributeValue::n);
        if(value == null) {
            return null;
        }
        return Long.parseLong(value);
    }
    public <T> void putNumber(String key, T value) {
        if (value == null || key == null) {
            return;
        }
        String stringNumber = String.valueOf(value);
        attributesMap.put(key, AttributeValue.builder().n(stringNumber).build());
    }
    public Integer getInteger(String key) {
        String value = get(key, AttributeValue::n);
        if(value == null) {
            return null;
        }
        return Integer.parseInt(value);
    }
    public Float getFloat(String key) {
        String value = get(key, AttributeValue::n);
        if(value == null) {
            return null;
        }
        return Float.parseFloat(value);
    }

    public <T extends BasicModel> T getObject(String key, T object) {
        Map<String, AttributeValue> value = get(key, AttributeValue::m);
        object.read(value);
        return object;
    }
    public <T extends BasicModel> void putObject(String key, T value) {
        if (value == null || key == null) {
            return;
        }
        Map<String, AttributeValue> map = value.save();
        attributesMap.put(key, AttributeValue.builder().m(map).build());
    }

    public List<String> getList(String key) {
        List<String> value = get(key, AttributeValue::ss);
        return value;
    }
    public void putList(String key, List<String> value) {
        if (value == null || key == null) {
            return;
        }
        attributesMap.put(key, AttributeValue.builder().ss(value).build());
    }

    private <T> T get(String key, Function<AttributeValue, T> function) {
        AttributeValue value = attributesMap.get(key);
        if(value != null) {
            return function.apply(value);
        }

        return null;
    }

}
