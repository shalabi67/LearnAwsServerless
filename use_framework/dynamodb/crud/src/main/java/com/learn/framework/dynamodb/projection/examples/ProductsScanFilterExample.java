package com.learn.framework.dynamodb.projection.examples;

import com.learn.framework.dynamodb.no_sort_key.product_catalog.models.Bicycle;
import com.learn.framework.dynamodb.no_sort_key.product_catalog.repositories.BicycleRepository;
import com.serverless.framework.dynamodb.factories.DynamodbClientFactory;
import com.serverless.framework.dynamodb.filtering.Filter;
import com.serverless.framework.dynamodb.repository.DynamodbAttributes;

import java.util.List;

public class ProductsScanFilterExample {
    public static void main(String[] args) {
        DynamodbAttributes filterValues = new DynamodbAttributes();
        filterValues.putString(":c", "Black");
        filterValues.putNumber(":p", 300.0);

        BicycleRepository bicycleRepository = new BicycleRepository(new DynamodbClientFactory());
        Bicycle bicycleModel = new Bicycle();
        bicycleModel.setFilter(new Filter("contains(Color, :c) and Price <= :p", filterValues));
        List<Bicycle> bicycleList = bicycleRepository.findAll(bicycleModel);

    }
}
