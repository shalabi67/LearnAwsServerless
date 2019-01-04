package com.learn.framework.dynamodb.projection.examples;

import com.learn.framework.dynamodb.no_sort_key.models.Bicycle;
import com.learn.framework.dynamodb.no_sort_key.models.Book;
import com.learn.framework.dynamodb.no_sort_key.repositories.BicycleRepository;
import com.learn.framework.dynamodb.no_sort_key.repositories.BookRepository;
import com.learn.framework.dynamodb.projection.model.ProductProjection;
import com.learn.framework.dynamodb.projection.repositories.ProductProjectionRepository;
import com.serverless.framework.dynamodb.factories.DynamodbClientFactory;

public class GetProductProjectionExample {
    public static void main(String[] args) {
        ProductProjectionRepository productProjectionRepository = new ProductProjectionRepository(new DynamodbClientFactory());
        ProductProjection productProjection = productProjectionRepository.find(new ProductProjection(123L));
        if(productProjection == null) {
            System.out.println("Could not find movie.");
        } else {
            System.out.println("movie found.");
        }


        //a simpler way no code needed for model or repository
        BicycleRepository bicycleRepository = new BicycleRepository(new DynamodbClientFactory());
        Bicycle bicycleModel = new Bicycle(123L);
        bicycleModel.setProjectionExpression("Description, RelatedItems[0], ProductReviews.FiveStar");
        Bicycle bicycle = bicycleRepository.find(bicycleModel);

    }
}
