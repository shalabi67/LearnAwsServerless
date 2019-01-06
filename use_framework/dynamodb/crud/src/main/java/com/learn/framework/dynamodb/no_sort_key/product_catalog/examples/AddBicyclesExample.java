package com.learn.framework.dynamodb.no_sort_key.product_catalog.examples;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.framework.dynamodb.no_sort_key.product_catalog.models.Bicycle;
import com.learn.framework.dynamodb.no_sort_key.product_catalog.repositories.BicycleRepository;
import com.serverless.framework.dynamodb.factories.DynamodbClientFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AddBicyclesExample {
    public static void main(String[] args) {
        ClassLoader classLoader = new AddBicyclesExample().getClass().getClassLoader();

        File file = new File(classLoader.getResource("bicycle.json").getFile());
        try {
            List<Bicycle> list = new ObjectMapper().readValue(file, new TypeReference<List<Bicycle>>(){});
            BicycleRepository bicycleRepository = new BicycleRepository(new DynamodbClientFactory());
            int i = 0;
            for(Bicycle bicycle : list) {
                bicycleRepository.save(bicycle);
                System.out.println("Bicycle Added = " + ++i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
