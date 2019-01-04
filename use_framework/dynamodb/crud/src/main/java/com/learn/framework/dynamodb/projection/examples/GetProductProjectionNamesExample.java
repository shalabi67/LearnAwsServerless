package com.learn.framework.dynamodb.projection.examples;

import com.learn.framework.dynamodb.no_sort_key.models.Bicycle;
import com.learn.framework.dynamodb.no_sort_key.repositories.BicycleRepository;
import com.learn.framework.dynamodb.projection.model.ProductProjection;
import com.learn.framework.dynamodb.projection.repositories.ProductProjectionRepository;
import com.serverless.framework.dynamodb.factories.DynamodbClientFactory;

import java.util.HashMap;
import java.util.Map;

public class GetProductProjectionNamesExample {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("#safty", "Safety.Warning");

        //a simpler way no code needed for model or repository
        BicycleRepository bicycleRepository = new BicycleRepository(new DynamodbClientFactory());
        Bicycle bicycleModel = new Bicycle(123L);
        bicycleModel.setProjectionExpression("#safty");
        bicycleModel.setExpressionAttributeNames(map);
        Bicycle bicycle = bicycleRepository.find(bicycleModel);

        Map<String, String> map1 = new HashMap<>();
        map1.put("#pr","ProductReviews");
        bicycleModel.setExpressionAttributeNames(map1);
        bicycleModel.setProjectionExpression("#pr.FiveStar, #pr.ThreeStar, #pr.OneStar");
        bicycle = bicycleRepository.find(bicycleModel);
    }
}
