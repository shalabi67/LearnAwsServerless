package com.serverless.framework.dynamodb.factories.types;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.function.Function;

public class DynamoNumberType<T, NUMBER> extends DynamoType<T> {
    private Function<String, NUMBER> stringToNumberFunction;
    private Function<NUMBER, String> numberToStringFunction;

    public DynamoNumberType(Function<T, AttributeValue.Builder> type, Function<AttributeValue, T> typeFunction) {
       super(type, typeFunction);
        stringToNumberFunction = null;
        numberToStringFunction = null;
    }

    public DynamoNumberType(Function<T, AttributeValue.Builder> builderFunction, Function<AttributeValue, T> typeFunction,
                            Function<String, NUMBER> stringToNumberFunction, Function<NUMBER, String> numberToStringFunction) {
        super(builderFunction, typeFunction);
        this.stringToNumberFunction = stringToNumberFunction;
        this.numberToStringFunction = numberToStringFunction;
    }

    public Function<String, NUMBER> getStringToNumberFunction() {
        return stringToNumberFunction;
    }

    public Function<NUMBER, String> getNumberToStringFunction() {
        return numberToStringFunction;
    }
}
