package com.serverless.framework.dynamodb.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;

public abstract class BaseModule implements BasicModel {
    @JsonIgnore
    protected DynamodbAttributes key;

    @JsonIgnore
	protected String tableName;

    protected BaseModule() {
    }

	protected abstract DynamodbAttributes createKey();

	public String getTableName() {
		return tableName;
	}

    public DynamodbAttributes getKey() {
        return createKey();
    }
}
