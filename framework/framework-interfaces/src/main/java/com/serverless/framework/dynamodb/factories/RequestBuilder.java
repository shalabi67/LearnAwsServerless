package com.serverless.framework.dynamodb.factories;

import com.serverless.framework.dynamodb.repository.BaseModule;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.UpdateItemRequest;

public class RequestBuilder {
    /**
     * This function is used with scan call
     * @param builder
     * @param model
     * @return
     */
    public static ScanRequest.Builder build(ScanRequest.Builder builder, BaseModule model) {
        if(model.getProjection() != null) {
            model.getProjection().setProjection(builder);
        }
        if(model.getFilter() != null) {
            model.getFilter().setFilter(builder);
        }

        return builder;
    }


    /**
     * This function is used with update calls
     * @param builder
     * @param model
     * @return
     */
    public static UpdateItemRequest.Builder build(UpdateItemRequest.Builder builder, BaseModule model) {
        if(model.getUpdate() != null) {
            model.getUpdate().setUpdate(builder);
        }

        return builder;
    }

    /**
     * This function is used with get item requests calls.
     * @param builder
     * @param model
     * @return
     */
    public static GetItemRequest.Builder build(GetItemRequest.Builder builder, BaseModule model) {
        if(model.getProjection() != null) {
            model.getProjection().setProjection(builder);
        }


        return builder;
    }
}
