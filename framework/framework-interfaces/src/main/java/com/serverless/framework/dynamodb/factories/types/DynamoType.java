package com.serverless.framework.dynamodb.factories.types;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.function.Function;

public class DynamoType<T> {
    protected Function<AttributeValue, T> typeFunction;
    protected Function<T, AttributeValue.Builder> builderFunction;

    public DynamoType(Function<T, AttributeValue.Builder> type, Function<AttributeValue, T> typeFunction) {
        this.typeFunction = typeFunction;
        this.builderFunction = type;
    }

    public Function<AttributeValue, T> getTypeFunction() {
        return typeFunction;
    }

    public Function<T, AttributeValue.Builder> getBuilderFunction() {
        return builderFunction;
    }
}
