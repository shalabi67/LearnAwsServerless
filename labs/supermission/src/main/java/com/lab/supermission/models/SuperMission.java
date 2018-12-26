package com.lab.supermission.models;

import com.serverless.framework.dynamodb.repository.BaseModule;
import com.serverless.framework.dynamodb.repository.DynamodbAttributes;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;

public class SuperMission extends BaseModule<String> {
    private static final String SUPER_HERO = "superHero";
    private static final String VILLAIN1 = "villain1";
    private static final String VILLAIN2 = "villain2";
    private static final String VILLAIN3 = "villain2";
    private static final String MISSION_STATUS = "missionStatus";
    private static final String SECRET_IDENTITY = "secretIdentity";

    private String superHero;
    private String villain1;
    private String villain2;
    private String villain3;
    private String missionStatus;
    private String secretIdentity;

    public SuperMission() {
        tableName = "lab-super-missions";
    }
    @Override
    public void read(Map<String, AttributeValue> map) {
        DynamodbAttributes attributes = new DynamodbAttributes(map);
        setId(attributes.getString(SUPER_HERO));
        villain1 = attributes.getString(VILLAIN1);
        villain2 = attributes.getString(VILLAIN2);
        villain3 = attributes.getString(VILLAIN3);
        missionStatus = attributes.getString(MISSION_STATUS);
        secretIdentity = attributes.getString(SECRET_IDENTITY);

    }

    @Override
    public Map<String, AttributeValue> save() {
        return null;
    }

    @Override
    protected String createId() {
        return getId();
    }

    @Override
    public String getId() {
        return superHero;
    }

    public void setId(String id) {
        this.id = id;
        this.superHero = id;
    }
}
