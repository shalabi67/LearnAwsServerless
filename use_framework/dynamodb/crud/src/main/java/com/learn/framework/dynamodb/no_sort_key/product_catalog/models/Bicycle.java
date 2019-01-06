package com.learn.framework.dynamodb.no_sort_key.product_catalog.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.serverless.framework.dynamodb.factories.types.DynamoTypeEnum;
import com.serverless.framework.dynamodb.repository.DynamodbAttributes;

import java.util.List;

public class Bicycle extends ProductCatalog {
    private static final String BICYCLE_TYPE = "BicycleType";
    private static final String BRAND = "Brand";
    private static final String COLOR = "Color";
    private static final String COMMENT = "Comment";
    public static final String DESCRIPTION = "Description";
    private static final String IN_STOCK = "InStock";
    private static final String PICTURES = "Pictures";
    public static final String RELATED_ITEMS = "RelatedItems";
    private static final String SAFETY_WARNING = "Safety.Warning";

    @JsonProperty("BicycleType")
    private String bicycleType;
    @JsonProperty("Brand")
    private String brand;
    @JsonProperty("Color")
    private List<String> colors;
    @JsonProperty("Comment")
    private String comment;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("InStock")
    private Boolean inStock;
    @JsonProperty("Pictures")
    private Picture pictures;
    @JsonProperty("RelatedItems")
    private List<Long> relatedItems;
    @JsonProperty("Safety.Warning")
    private String safetyWarning;

    public Bicycle() {
    }
    public Bicycle(Long id) {
        super(id);
    }

    @Override
    protected void readProductInformation(DynamodbAttributes attributes) {
        bicycleType = attributes.getString(BICYCLE_TYPE);
        brand = attributes.getString(BRAND);
        colors = attributes.getList(COLOR, DynamoTypeEnum.string);
        comment = attributes.getString(COMMENT);
        description = attributes.getString(DESCRIPTION);
        inStock = attributes.getBoolean(IN_STOCK);
        pictures = attributes.getObject(PICTURES, new Picture());
        relatedItems = attributes.getList(RELATED_ITEMS, DynamoTypeEnum.longNumber);
        safetyWarning = attributes.getString(SAFETY_WARNING);
    }

    @Override
    protected DynamodbAttributes saveProductInformation() {
        DynamodbAttributes attributes = new DynamodbAttributes();
        attributes.putString(BICYCLE_TYPE, bicycleType);
        attributes.putString(BRAND, brand);
        attributes.putList(COLOR, colors, DynamoTypeEnum.string);
        attributes.putString(COMMENT, comment);
        attributes.putString(DESCRIPTION, description);
        attributes.putBoolean(IN_STOCK, inStock);
        attributes.putObject(PICTURES, pictures);
        attributes.putList(RELATED_ITEMS, relatedItems, DynamoTypeEnum.longNumber);
        attributes.putString(SAFETY_WARNING, safetyWarning);

        return attributes;
    }
}