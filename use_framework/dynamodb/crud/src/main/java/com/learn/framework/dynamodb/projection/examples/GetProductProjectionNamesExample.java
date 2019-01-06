package com.learn.framework.dynamodb.projection.examples;

import com.learn.framework.dynamodb.no_sort_key.product_catalog.models.Bicycle;
import com.learn.framework.dynamodb.no_sort_key.product_catalog.repositories.BicycleRepository;
import com.serverless.framework.dynamodb.factories.DynamodbClientFactory;
import com.serverless.framework.dynamodb.projection.Projection;

import java.util.HashMap;
import java.util.Map;

public class GetProductProjectionNamesExample {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("#safty", "Safety.Warning");

        //a simpler way no code needed for model or repository
        BicycleRepository bicycleRepository = new BicycleRepository(new DynamodbClientFactory());
        Bicycle bicycleModel = new Bicycle(123L);
        bicycleModel.setProjection(new Projection("#safty", map));
        Bicycle bicycle = bicycleRepository.find(bicycleModel);

        Map<String, String> map1 = new HashMap<>();
        map1.put("#pr","ProductReviews");

        bicycleModel.setProjection(new Projection("#pr.FiveStar, #pr.ThreeStar, #pr.OneStar", map1));
        bicycle = bicycleRepository.find(bicycleModel);
    }
}
