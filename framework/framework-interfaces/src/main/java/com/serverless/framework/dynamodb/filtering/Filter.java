package com.serverless.framework.dynamodb.filtering;

import com.serverless.framework.dynamodb.repository.DynamodbAttributes;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;

public class Filter {
    private String filterExpression;
    private DynamodbAttributes expressionAttributeValues;

    public Filter(String filterExpression, DynamodbAttributes expressionAttributeValues) {
        this.filterExpression = filterExpression;
        this.expressionAttributeValues = expressionAttributeValues;
    }

    public ScanRequest.Builder setFilter(ScanRequest.Builder builder) {
        if(filterExpression != null && (!filterExpression.isEmpty())) {
            builder.filterExpression(filterExpression);
            if(expressionAttributeValues != null) {
                builder.expressionAttributeValues(expressionAttributeValues.getAttributesMap());
            }
        }

        return builder;
    }
}
