package com.learnaws.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.learnaws.dynamodb.model.Bicycle;
import com.learnaws.dynamodb.model.Movie;
import com.learnaws.dynamodb.repository.MoviesRepository;
import com.learnaws.dynamodb.repository.ProductCatalogRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryCategoryWithIndexExample {
    public static void main(String[] args) {
        Map<String,String> expressionAttributesNames = new HashMap<>();
        expressionAttributesNames.put("#ProductCategory","ProductCategory");
        expressionAttributesNames.put("#Price","Price");

        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":productCategoryParam", new AttributeValue().withS("Bicycle"));
        eav.put(":priceParam", new AttributeValue().withN("200"));

        DynamoDBQueryExpression<Bicycle> queryExpression = new DynamoDBQueryExpression<Bicycle>()
                .withIndexName("ProductCategory-Price-index")
                .withConsistentRead(false)
                .withKeyConditionExpression("#ProductCategory = :productCategoryParam and #Price > :priceParam")
                .withExpressionAttributeNames(expressionAttributesNames)
                .withExpressionAttributeValues(eav);

        ProductCatalogRepository<Bicycle> bicycleProductCatalogRepository = new ProductCatalogRepository<Bicycle>();
        List<Bicycle> bicycles = bicycleProductCatalogRepository.query(Bicycle.class, queryExpression);
        for(Bicycle bicycle : bicycles){
            System.out.println(bicycle.toString());
        }
    }
}
