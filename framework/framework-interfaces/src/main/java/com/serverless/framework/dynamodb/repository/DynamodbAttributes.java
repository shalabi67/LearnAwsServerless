package com.serverless.framework.dynamodb.repository;

import software.amazon.awssdk.services.dynamodb.model.AttributeAction;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.AttributeValueUpdate;

import java.util.*;
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

    public DynamodbAttributes(BaseModule module, Boolean removeKeys) {
        Map<String, AttributeValue> keyMap = module.createKey().getAttributesMap();
        Map<String, AttributeValue> attributes = module.save();
        if(removeKeys) {
            for (String keyName : keyMap.keySet()) {
                attributes.remove(keyName);
            }
        }

        this.attributesMap = attributes;
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

    public List<String> getEnum(String key) {
        List<String> value = get(key, AttributeValue::ss);
        return value;
    }
    public void putEnum(String key, List<String> value) {
        if (value == null || key == null) {
            return;
        }
        attributesMap.put(key, AttributeValue.builder().ss(value).build());
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

    public  void putObject(String key, Map<String, AttributeValue> value) {
        if (value == null || key == null) {
            return;
        }

        attributesMap.put(key, AttributeValue.builder().m(value).build());
    }

    public List<String> getList(String key) {
        List<AttributeValue> value = get(key, AttributeValue::l);
        List<String> list = new ArrayList<>();
        for(AttributeValue attributeValue : value) {
            list.add(get(attributeValue, AttributeValue::s));
        }
        return list;
    }
    public void putList(String key, List<String> value) {
        if (value == null || key == null) {
            return;
        }
        List<AttributeValue> list = new ArrayList<>();
        for(String s : value) {
            list.add(AttributeValue.builder().s(s).build());
        }
        attributesMap.put(key, AttributeValue.builder().l(list).build());
    }

    public HashMap<String,AttributeValueUpdate> getUpdateAttributes() {
        HashMap<String,AttributeValueUpdate> map = new HashMap<String,AttributeValueUpdate>();
        for(String key : attributesMap.keySet()) {
            AttributeValueUpdate attribute = AttributeValueUpdate.builder()
                    .value(attributesMap.get(key))
                    .action(AttributeAction.PUT)
                    .build();
            map.put(key, attribute);
        }

        return map;
    }

    private <T> T get(String key, Function<AttributeValue, T> function) {
        AttributeValue value = attributesMap.get(key);
        if(value != null) {
            return function.apply(value);
        }

        return null;
    }

    private <T> T get( AttributeValue value, Function<AttributeValue, T> function) {
        return function.apply(value);
    }


}
