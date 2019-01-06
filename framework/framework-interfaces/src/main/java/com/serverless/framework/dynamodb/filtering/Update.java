package com.serverless.framework.dynamodb.filtering;

import com.serverless.framework.dynamodb.repository.DynamodbAttributes;
import software.amazon.awssdk.services.dynamodb.model.UpdateItemRequest;

import java.util.Map;

public class Update {
    private String updateExpression;
    private DynamodbAttributes expressionAttributeValues;
    private Map<String, String> expressionAttributeNamesMap;

    public Update(String updateExpression) {
        this.updateExpression = updateExpression;
    }

    public Update(String updateExpression, DynamodbAttributes expressionAttributeValues) {
        this.updateExpression = updateExpression;
        this.expressionAttributeValues = expressionAttributeValues;
    }

    public Update(String updateExpression, DynamodbAttributes expressionAttributeValues, Map<String, String> expressionAttributeNamesMap) {
        this.updateExpression = updateExpression;
        this.expressionAttributeValues = expressionAttributeValues;
        this.expressionAttributeNamesMap = expressionAttributeNamesMap;
    }

    public UpdateItemRequest.Builder setUpdate(UpdateItemRequest.Builder builder) {
        if(updateExpression != null && (!updateExpression.isEmpty())) {
            builder.updateExpression(updateExpression);
            if(expressionAttributeValues != null) {
                builder.expressionAttributeValues(expressionAttributeValues.getAttributesMap());
            }
            if(expressionAttributeNamesMap != null) {
                builder.expressionAttributeNames(expressionAttributeNamesMap);
            }
        }

        return builder;
    }
}
