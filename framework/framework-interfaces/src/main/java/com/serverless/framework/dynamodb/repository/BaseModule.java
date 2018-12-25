package com.serverless.framework.dynamodb.repository;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;
import java.util.UUID;

public abstract class BaseModule<T> {
	protected static final String ID = "id";

	protected T id;
	protected String tableName;

	protected BaseModule() {
		id = createId();
	}
	public abstract void read();
	public abstract Map<String, AttributeValue> save();
	protected abstract T createId();

	public T getId() {
		return id;
	}

	public String getTableName() {
		return tableName;
	}
}
