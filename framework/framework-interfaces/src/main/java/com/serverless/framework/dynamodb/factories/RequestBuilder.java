package com.serverless.framework.dynamodb.factories;

import com.serverless.framework.dynamodb.repository.BaseModule;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;

public class RequestBuilder {
    public static ScanRequest.Builder build(ScanRequest.Builder builder, BaseModule model) {
        if(model.getProjection() != null) {
            model.getProjection().setProjection(builder);
        }
        if(model.getFilter() != null) {
            model.getFilter().setFilter(builder);
        }

        return builder;
    }

    public static GetItemRequest.Builder build(GetItemRequest.Builder builder, BaseModule model) {
        if(model.getProjection() != null) {
            model.getProjection().setProjection(builder);
        }


        return builder;
    }
}
