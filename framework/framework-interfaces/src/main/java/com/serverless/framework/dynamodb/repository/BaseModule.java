package com.serverless.framework.dynamodb.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;

public abstract class BaseModule implements BasicModel {
    @JsonIgnore
    protected DynamodbAttributes key;

    protected BaseModule() {
    }

	protected abstract DynamodbAttributes createKey();

	public abstract String getTableName();

    public DynamodbAttributes getKey() {
        return createKey();
    }
}
