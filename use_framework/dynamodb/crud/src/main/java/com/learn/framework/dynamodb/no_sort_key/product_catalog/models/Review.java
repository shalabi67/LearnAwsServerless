package com.learn.framework.dynamodb.no_sort_key.product_catalog.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.serverless.framework.dynamodb.factories.types.DynamoTypeEnum;
import com.serverless.framework.dynamodb.repository.BasicModel;
import com.serverless.framework.dynamodb.repository.DynamodbAttributes;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.List;
import java.util.Map;

public class Review implements BasicModel {
    private static final String FIVE_STARS = "FiveStar";
    private static final String FOUR_STARS = "FourStar";
    private static final String THREE_STARS = "ThreeStar";
    private static final String TWO_STARS = "TwoStar";
    private static final String ONE_STARS = "OneStar";

    @JsonProperty("FiveStar")
    private List<String> fiveStars;
    @JsonProperty("FourStar")
    private List<String> fourStars;
    @JsonProperty("ThreeStar")
    private List<String> threeStars;
    @JsonProperty("TwoStar")
    private List<String> twoStars;
    @JsonProperty("OneStar")
    private List<String> oneStars;

    @Override
    public void read(Map<String, AttributeValue> map) {
        DynamodbAttributes attributes = new DynamodbAttributes(map);
        fiveStars = attributes.getList(FIVE_STARS, DynamoTypeEnum.string);
        fourStars = attributes.getList(FOUR_STARS, DynamoTypeEnum.string);
        threeStars = attributes.getList(THREE_STARS, DynamoTypeEnum.string);
        twoStars = attributes.getList(TWO_STARS, DynamoTypeEnum.string);
        oneStars = attributes.getList(ONE_STARS, DynamoTypeEnum.string);
    }

    @Override
    public Map<String, AttributeValue> save() {
        DynamodbAttributes attributes = new DynamodbAttributes();
        attributes.putList(FIVE_STARS, fiveStars, DynamoTypeEnum.string);
        attributes.putList(FOUR_STARS, fourStars, DynamoTypeEnum.string);
        attributes.putList(THREE_STARS, threeStars, DynamoTypeEnum.string);
        attributes.putList(TWO_STARS, twoStars, DynamoTypeEnum.string);
        attributes.putList(ONE_STARS, oneStars, DynamoTypeEnum.string);
        return attributes.getAttributesMap();
    }

    public List<String> getFiveStars() {
        return fiveStars;
    }

    public void setFiveStars(List<String> fiveStars) {
        this.fiveStars = fiveStars;
    }

    public List<String> getFourStars() {
        return fourStars;
    }

    public void setFourStars(List<String> fourStars) {
        this.fourStars = fourStars;
    }

    public List<String> getThreeStars() {
        return threeStars;
    }

    public void setThreeStars(List<String> threeStars) {
        this.threeStars = threeStars;
    }

    public List<String> getTwoStars() {
        return twoStars;
    }

    public void setTwoStars(List<String> twoStars) {
        this.twoStars = twoStars;
    }

    public List<String> getOneStars() {
        return oneStars;
    }

    public void setOneStars(List<String> oneStars) {
        this.oneStars = oneStars;
    }
}
