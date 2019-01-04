package com.serverless.framework.dynamodb.repository;

import com.serverless.framework.dynamodb.factories.ClassFactory;
import com.serverless.framework.dynamodb.factories.types.DynamoNumberType;
import com.serverless.framework.dynamodb.factories.types.DynamoType;
import com.serverless.framework.dynamodb.factories.types.DynamoTypeEnum;
import com.serverless.framework.dynamodb.factories.types.DynamoTypeFactory;
import com.sun.org.apache.xpath.internal.operations.Bool;
import software.amazon.awssdk.services.dynamodb.model.AttributeAction;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.AttributeValueUpdate;

import java.lang.reflect.Type;
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

    public Boolean getBoolean(String key) {
        Boolean value = get(key, AttributeValue::bool);
        return value;
    }
    public void putBoolean(String key, Boolean value) {
        if (value == null || key == null) {
            return;
        }
        attributesMap.put(key, AttributeValue.builder().bool(value).build());
    }

    public <T extends BasicModel> T getObject(String key, T object) {
        Map<String, AttributeValue> value = get(key, AttributeValue::m);
        if(value == null) {
            return null;
        }
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

    @Deprecated
    public List<String> getList(String key) {
        return getList(key, DynamoTypeEnum.string);
        /*
        List<AttributeValue> value = get(key, AttributeValue::l);
        if(value == null) {
            return new ArrayList<>();
        }
        List<String> list = new ArrayList<>();
        for(AttributeValue attributeValue : value) {
            list.add(get(attributeValue, AttributeValue::s));
        }
        return list;
        */
    }
    @Deprecated
    public void putList(String key, List<String> value) {
        putList(key, value, DynamoTypeEnum.string);
        /*
        if (value == null || key == null) {
            return;
        }
        List<AttributeValue> list = new ArrayList<>();
        for(String s : value) {
            list.add(AttributeValue.builder().s(s).build());
        }
        attributesMap.put(key, AttributeValue.builder().l(list).build());
        */
    }

    public <T> List<T> getList(String key, DynamoTypeEnum typeEnum) {
        DynamoType<T> type = DynamoTypeFactory.create(typeEnum);
        List<AttributeValue> value = get(key, AttributeValue::l);
        if(value == null) {
            return new ArrayList<>();
        }
        List<T> list = new ArrayList<>();
        for(AttributeValue attributeValue : value) {
            if(type instanceof DynamoNumberType) {
                DynamoNumberType<String, T> numberType = (DynamoNumberType<String, T>) type;
                String stringValue = (String)get(attributeValue, type.getTypeFunction());
                if(stringValue == null) {
                    continue;
                }
                list.add(numberType.getStringToNumberFunction().apply(stringValue));

            } else {
                list.add(get(attributeValue, type.getTypeFunction()));
            }
        }
        return list;
    }
    public <T> void putList(String key, List<T> value, DynamoTypeEnum typeEnum) {
        if (value == null || key == null) {
            return;
        }
        DynamoType<T> type = DynamoTypeFactory.create(typeEnum);
        List<AttributeValue> list = new ArrayList<>();
        for(T s : value) {
            if(type instanceof DynamoNumberType) {
                DynamoNumberType<String, T> numberType = (DynamoNumberType<String, T>) type;
                String item = numberType.getNumberToStringFunction().apply(s);
                list.add(numberType.getBuilderFunction().apply(item).build());
            }else {
                list.add(type.getBuilderFunction().apply(s).build());
            }
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
