package com.serverless.framework.dynamodb.query;

import com.serverless.framework.dynamodb.repository.DynamodbAttributes;
import software.amazon.awssdk.services.dynamodb.model.QueryRequest;

import java.util.Map;

public class Query {
    private String keyConditionExpression;
    private String filterExpression;
    private DynamodbAttributes expressionAttributeValues;
    private Map<String, String> expressionAttributeNamesMap;
    private Integer limit;
    private Integer pageSize;

    public Query(String filterExpression, DynamodbAttributes expressionAttributeValues) {
        this.keyConditionExpression = filterExpression;
        this.expressionAttributeValues = expressionAttributeValues;
    }

    public Query(String keyConditionExpression, String filterExpression, DynamodbAttributes expressionAttributeValues) {
        this.keyConditionExpression = keyConditionExpression;
        this.filterExpression = filterExpression;
        this.expressionAttributeValues = expressionAttributeValues;
    }

    public Query(String keyConditionExpression, String filterExpression, DynamodbAttributes expressionAttributeValues,
                 Map<String, String> expressionAttributeNamesMap) {
        this.keyConditionExpression = keyConditionExpression;
        this.filterExpression = filterExpression;
        this.expressionAttributeValues = expressionAttributeValues;
        this.expressionAttributeNamesMap = expressionAttributeNamesMap;
    }

    public QueryRequest.Builder setQuery(QueryRequest.Builder builder) {
        if(keyConditionExpression != null && (!keyConditionExpression.isEmpty())) {
            builder.keyConditionExpression(keyConditionExpression);
            if(expressionAttributeValues != null) {
                builder.expressionAttributeValues(expressionAttributeValues.getAttributesMap());
            }
            if(filterExpression != null) {
                builder.filterExpression(filterExpression);
            }
            if(expressionAttributeNamesMap != null) {
                builder.expressionAttributeNames(expressionAttributeNamesMap);
            }
            if(limit != null) {
                builder.limit(limit);
            }
        }

        return builder;
    }
}
