package com.learn.framework.dynamodb.no_sort_key.product_catalog.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.serverless.framework.dynamodb.repository.BasicModel;
import com.serverless.framework.dynamodb.repository.DynamodbAttributes;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;

public class Picture implements BasicModel {
    private static final String FRONT_VIEW = "FrontView";
    private static final String REAR_VIEW = "RearView";
    private static final String SIDE_VIEW = "SideView";

    @JsonProperty(FRONT_VIEW)
    private String frontView;
    @JsonProperty(REAR_VIEW)
    private String rearView;
    @JsonProperty(SIDE_VIEW)
    private String sideView;

    public Picture() {

    }
    public Picture(String frontView, String rearView, String sideView) {
        this.frontView = frontView;
        this.rearView = rearView;
        sideView = sideView;
    }

    @Override
    public void read(Map<String, AttributeValue> map) {
        DynamodbAttributes attributes = new DynamodbAttributes(map);
        frontView = attributes.getString(FRONT_VIEW);
        rearView = attributes.getString(REAR_VIEW);
        sideView = attributes.getString(SIDE_VIEW);
    }

    @Override
    public Map<String, AttributeValue> save() {
        DynamodbAttributes attributes = new DynamodbAttributes();
        attributes.putString(FRONT_VIEW, frontView);
        attributes.putString(REAR_VIEW, rearView);
        attributes.putString(SIDE_VIEW, sideView);
        return attributes.getAttributesMap();
    }

    public String getFrontView() {
        return frontView;
    }

    public void setFrontView(String frontView) {
        this.frontView = frontView;
    }

    public String getRearView() {
        return rearView;
    }

    public void setRearView(String rearView) {
        this.rearView = rearView;
    }

    public String getSideView() {
        return sideView;
    }

    public void setSideView(String sideView) {
        sideView = sideView;
    }


}
