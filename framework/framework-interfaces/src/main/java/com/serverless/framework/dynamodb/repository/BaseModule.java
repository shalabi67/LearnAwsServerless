package com.serverless.framework.dynamodb.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;

public abstract class BaseModule implements BasicModel {
    @JsonIgnore
    protected DynamodbAttributes key;

    @JsonIgnore
    protected String projectionExpression;

    @JsonIgnore
    protected Map<String, String> expressionAttributeNames;

    protected BaseModule() {
    }

	protected abstract DynamodbAttributes createKey();

	public abstract String getTableName();

    public DynamodbAttributes getKey() {
        return createKey();
    }

    public String getProjectionExpression() {
        return projectionExpression;
    }

    public void setProjectionExpression(String projectionExpression) {
        this.projectionExpression = projectionExpression;
    }

    public Map<String, String> getExpressionAttributeNames() {
        return expressionAttributeNames;
    }

    public String getExpressionNames() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(expressionAttributeNames);
        } catch (JsonProcessingException e) {
            return "";
        }
    }

    public void setExpressionAttributeNames(Map<String, String> expressionAttributeNames) {
        this.expressionAttributeNames = expressionAttributeNames;
    }
}
