package com.serverless.framework.dynamodb.exceptions;

public class ModelInstantiationException extends RuntimeException {
    public ModelInstantiationException(String message, Throwable cause) {
        super(message, cause);
    }
}
