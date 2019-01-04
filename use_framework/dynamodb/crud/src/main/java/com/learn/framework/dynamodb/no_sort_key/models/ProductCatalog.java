package com.learn.framework.dynamodb.no_sort_key.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.serverless.framework.dynamodb.repository.BaseModule;
import com.serverless.framework.dynamodb.repository.DynamodbAttributes;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;

public abstract class ProductCatalog extends BaseModule {
    private static final String TABLE_NAME = "learn-product-catalog";
    public static final String ID = "Id";
    private static final String PRICE = "Price";
    private static final String PRODUCT_CATEGORY = "ProductCategory";
    private static final String PRODUCT_REVIEWS = "ProductReviews";
    private static final String QUANTITY_ON_HAND = "QuantityOnHand";
    private static final String TITLE = "Title";

    @JsonProperty("Id")
    private Long id;
    @JsonProperty("Price")
    private Float price;
    @JsonProperty("ProductCategory")
    private String productCategory;
    @JsonProperty("ProductReviews")
    Review productReviews;
    @JsonProperty("QuantityOnHand")
    private Integer quantityOnHand;
    @JsonProperty("Title")
    private String title;

    public ProductCatalog() {

    }
    public ProductCatalog(Long id) {
        this.id = id;
    }

    @Override
    protected DynamodbAttributes createKey() {
        DynamodbAttributes key = new DynamodbAttributes();
        key.putNumber(ID, id);
        return key;
    }

    protected abstract void readProductInformation(DynamodbAttributes attributes);
    protected abstract DynamodbAttributes saveProductInformation();


    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public void read(Map<String, AttributeValue> map) {
        DynamodbAttributes attributes = new DynamodbAttributes(map);
        readProductInformation(attributes);

        id = attributes.getLong(ID);
        price = attributes.getFloat(PRICE);
        productCategory = attributes.getString(PRODUCT_CATEGORY);
        productReviews = attributes.getObject(PRODUCT_REVIEWS, new Review());
        quantityOnHand = attributes.getInteger(QUANTITY_ON_HAND);
        title = attributes.getString(TITLE);
    }

    @Override
    public Map<String, AttributeValue> save() {
        DynamodbAttributes attributes = saveProductInformation();

        attributes.putNumber(ID, id);
        attributes.putNumber(PRICE, price);
        attributes.putString(PRODUCT_CATEGORY, productCategory);
        attributes.putObject(PRODUCT_REVIEWS, productReviews);
        attributes.putNumber(QUANTITY_ON_HAND, quantityOnHand);
        attributes.putString(TITLE, title);

        return attributes.getAttributesMap();
    }
}
