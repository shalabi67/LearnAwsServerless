package com.serverless.framework.dynamodb.repository;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;

public interface BasicModel {
    void read(Map<String, AttributeValue> stringAttributeValueMap);
    Map<String, AttributeValue> save();;
}
