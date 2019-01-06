package com.serverless.framework.dynamodb.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverless.framework.dynamodb.filtering.Filter;
import com.serverless.framework.dynamodb.filtering.Update;
import com.serverless.framework.dynamodb.projection.Projection;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;

public abstract class BaseModule implements BasicModel {
    @JsonIgnore
    protected DynamodbAttributes key;

    @JsonIgnore
    private Projection projection;

    @JsonIgnore
    private Filter filter;

    @JsonIgnore
    private Update update;

    protected BaseModule() {
    }

	protected abstract DynamodbAttributes createKey();

	public abstract String getTableName();

    public DynamodbAttributes getKey() {
        return createKey();
    }

    public Projection getProjection() {
        return projection;
    }

    public void setProjection(Projection projection) {
        this.projection = projection;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public void setKey(DynamodbAttributes key) {
        this.key = key;
    }

    public Update getUpdate() {
        return update;
    }

    public void setUpdate(Update update) {
        this.update = update;
    }
}
