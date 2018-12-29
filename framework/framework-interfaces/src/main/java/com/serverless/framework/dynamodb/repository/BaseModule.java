package com.serverless.framework.dynamodb.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;

public abstract class BaseModule<T> implements BasicModel {
	protected static final String ID = "id";

	protected T id;
    @JsonIgnore
	protected String tableName;

	protected BaseModule() {
		id = createId();
	}
	protected abstract T createId();

	public T getId() {
		return id;
	}

	public String getTableName() {
		return tableName;
	}

	@JsonIgnore
	public String getKeyName() {
		return ID;
	}
}
