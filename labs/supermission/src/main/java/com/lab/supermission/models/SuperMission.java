package com.lab.supermission.models;

import com.serverless.framework.dynamodb.repository.BaseModule;
import com.serverless.framework.dynamodb.repository.DynamodbAttributes;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;

public class SuperMission extends BaseModule<String> {
    public static final String SUPER_HERO = "superHero";
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
    public String getKeyName() {
        return SUPER_HERO;
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
        DynamodbAttributes attributes = new DynamodbAttributes();
        attributes.putString(SUPER_HERO, superHero);
        if(villain1 != null) {
            attributes.putString(VILLAIN1, villain1);
        }
        if(villain2 != null) {
            attributes.putString(VILLAIN2, villain2);
        }
        if(villain3 != null) {
            attributes.putString(VILLAIN3, villain3);
        }
        if(missionStatus != null) {
            attributes.putString(MISSION_STATUS, missionStatus);
        }
        if(secretIdentity != null) {
            attributes.putString(SECRET_IDENTITY, secretIdentity);
        }

        return attributes.getAttributesMap();
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

    public String getSuperHero() {
        return superHero;
    }

    public void setSuperHero(String superHero) {
        this.superHero = superHero;
        id = superHero;
    }

    public String getVillain1() {
        return villain1;
    }

    public void setVillain1(String villain1) {
        this.villain1 = villain1;
    }

    public String getVillain2() {
        return villain2;
    }

    public void setVillain2(String villain2) {
        this.villain2 = villain2;
    }

    public String getVillain3() {
        return villain3;
    }

    public void setVillain3(String villain3) {
        this.villain3 = villain3;
    }

    public String getMissionStatus() {
        return missionStatus;
    }

    public void setMissionStatus(String missionStatus) {
        this.missionStatus = missionStatus;
    }

    public String getSecretIdentity() {
        return secretIdentity;
    }

    public void setSecretIdentity(String secretIdentity) {
        this.secretIdentity = secretIdentity;
    }
}
