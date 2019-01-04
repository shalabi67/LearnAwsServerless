package com.serverless.framework.dynamodb.projection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;

import java.util.Map;

public class Projection {
    @JsonIgnore
    private String projectionExpression;

    @JsonIgnore
    private Map<String, String> expressionAttributeNamesMap;

    public Projection(String projectionExpression, Map<String, String> expressionAttributeNames) {
        this.projectionExpression = projectionExpression;
        this.expressionAttributeNamesMap = expressionAttributeNames;
    }

    public GetItemRequest.Builder setProjection(GetItemRequest.Builder builder) {
        if(projectionExpression!=null && (!projectionExpression.isEmpty())) {
            builder.projectionExpression(projectionExpression);
            if(expressionAttributeNamesMap!=null && expressionAttributeNamesMap.size()>0) {
                builder.expressionAttributeNames(expressionAttributeNamesMap);
            }
        }

        return builder;
    }
    public ScanRequest.Builder setProjection(ScanRequest.Builder builder) {
        if(projectionExpression!=null && (!projectionExpression.isEmpty())) {
            builder.projectionExpression(projectionExpression);
            if(expressionAttributeNamesMap!=null && expressionAttributeNamesMap.size()>0) {
                builder.expressionAttributeNames(expressionAttributeNamesMap);
            }
        }

        return builder;
    }

}
