package com.learn.framework.dynamodb.projection.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learn.framework.dynamodb.no_sort_key.product_catalog.models.Bicycle;
import com.learn.framework.dynamodb.no_sort_key.product_catalog.models.ProductCatalog;
import com.learn.framework.dynamodb.no_sort_key.product_catalog.models.Review;
import com.serverless.framework.dynamodb.factories.types.DynamoTypeEnum;
import com.serverless.framework.dynamodb.projection.Projection;
import com.serverless.framework.dynamodb.repository.BaseModule;
import com.serverless.framework.dynamodb.repository.DynamodbAttributes;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.List;
import java.util.Map;

import static com.learn.framework.dynamodb.no_sort_key.product_catalog.models.ProductCatalog.ID;

public class ProductProjection extends BaseModule {

    @JsonProperty(ID)
    private Long id;

    @JsonProperty(Bicycle.DESCRIPTION)
    private String description;

    @JsonProperty(ProductCatalog.PRODUCT_REVIEWS)
    Review productReviews;

    @JsonProperty(Bicycle.RELATED_ITEMS)
    private List<Long> relatedItems;

    public ProductProjection() {
        setProjection(createProjection());
    }
    public ProductProjection(Long id) {
        this.id = id;
        setProjection(createProjection());
    }
    private Projection createProjection() {
        return new Projection("Description, RelatedItems[0], ProductReviews.FiveStar", null);
    }

    @Override
    protected DynamodbAttributes createKey() {
        DynamodbAttributes key = new DynamodbAttributes();
        key.putNumber(ID, id);
        return key;
    }

    @Override
    public String getTableName() {
        return ProductCatalog.TABLE_NAME;
    }

    @Override
    public void read(Map<String, AttributeValue> map) {
        DynamodbAttributes attributes = new DynamodbAttributes(map);
        id = attributes.getLong(ID);
        description = attributes.getString(Bicycle.DESCRIPTION);
        relatedItems = attributes.getList(Bicycle.RELATED_ITEMS, DynamoTypeEnum.longNumber);
        productReviews = attributes.getObject(ProductCatalog.PRODUCT_REVIEWS, new Review());
    }

    @Override
    public Map<String, AttributeValue> save() {
        return null;
    }
}
