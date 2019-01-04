package com.serverless.framework.dynamodb.factories.types;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.lang.reflect.Type;

public class DynamoTypeFactory {
    public static DynamoType create(DynamoTypeEnum type) {
        switch (type) {
            case floatNumber: return new DynamoNumberType<>(AttributeValue.builder()::n, AttributeValue::n, Float::parseFloat, String::valueOf);
            case longNumber: return new DynamoNumberType<>(AttributeValue.builder()::n, AttributeValue::n, Long::parseLong, String::valueOf);
            case intNumber: return new DynamoNumberType<>(AttributeValue.builder()::n, AttributeValue::n, Integer::parseInt, String::valueOf);
            case string: return new DynamoType<>(AttributeValue.builder()::s, AttributeValue::s);
            case bool: return new DynamoType<>(AttributeValue.builder()::bool, AttributeValue::bool);
            case list: return new DynamoType<>(AttributeValue.builder()::l, AttributeValue::l);
            case object: return new DynamoType<>(AttributeValue.builder()::m, AttributeValue::m);
            case enumerator: return new DynamoType<>(AttributeValue.builder()::ss, AttributeValue::ss);
            default: return null;
        }
    }
}
