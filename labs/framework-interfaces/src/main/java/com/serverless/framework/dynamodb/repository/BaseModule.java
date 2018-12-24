package com.serverless.framework.dynamodb.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
public abstract class BaseModule<T> {
	private T key;
	private String tableName;

	public abstract void read();
	public abstract Map<String, AttributeValue> save();
}
