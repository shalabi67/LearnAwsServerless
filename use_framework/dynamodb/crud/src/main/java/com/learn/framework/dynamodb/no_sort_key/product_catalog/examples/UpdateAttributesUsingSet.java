package com.learn.framework.dynamodb.no_sort_key.product_catalog.examples;

import com.learn.framework.dynamodb.no_sort_key.product_catalog.models.Bicycle;
import com.learn.framework.dynamodb.no_sort_key.product_catalog.repositories.BicycleRepository;
import com.serverless.framework.dynamodb.factories.DynamodbClientFactory;
import com.serverless.framework.dynamodb.factories.types.DynamoTypeEnum;
import com.serverless.framework.dynamodb.filtering.Update;
import com.serverless.framework.dynamodb.repository.DynamodbAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/Expressions.UpdateExpressions.html
 */
public class UpdateAttributesUsingSet {
    public static void main(String[] args) {
        BicycleRepository bicycleRepository = new BicycleRepository(new DynamodbClientFactory());

        /**
         * examples for using SET to do update
         */
        //UpdateProductCategoryAndPrice(bicycleRepository);
        //AddListAndMapTOItem(bicycleRepository);
        //AddElementToList(bicycleRepository);
        //AddNestedMap(bicycleRepository);
        //Atomic Incrementing and Decrementing Numeric Attributes
        //AtomicDecreasePrice(bicycleRepository);
        //AppendToList(bicycleRepository);
        //AddAtListBeginning(bicycleRepository);

        /**
         * examples to use REMOVE
         */
        //RemoveAttributes(bicycleRepository);
        //RemoveListElements(bicycleRepository);

        /**
         * examples of using ADD
         * It is recommnded to use SET instead of ADD, but you use add only on the following cae:
         * use add only if you want to increment element value. this will create the attribute if it does not exist.
         */
        IncrementAttributeValue(bicycleRepository);

    }

    private static void UpdateProductCategoryAndPrice(BicycleRepository bicycleRepository) {
        //Update the ProductCategory and Price attributes
        Bicycle bicycle = new Bicycle(789L);
        DynamodbAttributes categoryPriceAttributes = new DynamodbAttributes();
        categoryPriceAttributes.putString(":c", "Hardware");
        categoryPriceAttributes.putNumber(":p", 60F);
        bicycle.setUpdate(new Update("SET ProductCategory = :c, Price = :p", categoryPriceAttributes));
        bicycleRepository.update(bicycle);

        /*
        {
    ":c": { "S": "Hardware" },
    ":p": { "N": "60" }
}
         */
    }

    private static void AddListAndMapTOItem(BicycleRepository bicycleRepository) {
        //Add a new list and a new map
        Bicycle bicycle = new Bicycle(789L);
        List<String> hammerList = new ArrayList<>();
        hammerList.add("Hammer");

        List<String> fiveStartList = new ArrayList<>();
        fiveStartList.add("Hammer");

        DynamodbAttributes attributes = new DynamodbAttributes();
        attributes.putList("FiveStar", fiveStartList, DynamoTypeEnum.string);

        DynamodbAttributes categoryPriceAttributes = new DynamodbAttributes();
        categoryPriceAttributes.putList(":ri", hammerList, DynamoTypeEnum.string);
        categoryPriceAttributes.putMap(":pr", attributes);
        bicycle.setUpdate(new Update("SET RelatedItems = :ri, ProductReviews = :pr", categoryPriceAttributes));
        bicycleRepository.update(bicycle);
        /*
        {
    ":ri": {
        "L": [
            { "S": "Hammer" }
        ]
    },
    ":pr": {
        "M": {
            "FiveStar": {
                "L": [
                    { "S": "Best product ever!" }
                ]
            }
        }
    }
}
         */
    }

    //Adding Elements To a List
    private static void AddElementToList(BicycleRepository bicycleRepository) {
        //initialize data of the item 789
        AddListAndMapTOItem(bicycleRepository);

        //Adding Elements To RelatedItems List
        Bicycle bicycle = new Bicycle(789L);
        DynamodbAttributes relatedItemsAttributes = new DynamodbAttributes();
        relatedItemsAttributes.putString(":ri", "Nails");
        bicycle.setUpdate(new Update("SET RelatedItems[100] = :ri", relatedItemsAttributes));
        bicycleRepository.update(bicycle);

        /*
        {
    ":ri": { "S": "Nails" }
}
         */
    }

    //Adding Nested Map Attributes
    private static void AddNestedMap(BicycleRepository bicycleRepository) {
        //initialize data of the item 789
        AddListAndMapTOItem(bicycleRepository);

        Map<String, String> attributeNames = new HashMap<>();
        attributeNames.put("#pr", "ProductReviews");
        attributeNames.put("#5star", "FiveStar");
        attributeNames.put("#3star", "ThreeStar");

        List<String> list = new ArrayList<>();
        list.add("Just OK - not that great");


        //Adding Nested Map Attributes
        Bicycle bicycle = new Bicycle(789L);
        DynamodbAttributes relatedItemsAttributes = new DynamodbAttributes();
        relatedItemsAttributes.putString(":r5", "Very happy with my purchase");
        relatedItemsAttributes.putList(":r3", list, DynamoTypeEnum.string);
        bicycle.setUpdate(new Update("SET #pr.#5star[1] = :r5, #pr.#3star = :r3", relatedItemsAttributes, attributeNames));
        bicycleRepository.update(bicycle);

        /*
        {
    ":r5": { "S": "Very happy with my purchase" },
    ":r3": {
        "L": [
            { "S": "Just OK - not that great" }
        ]
    }
}
         */
    }

    //Incrementing and Decrementing Numeric Attributes
    private static void AtomicDecreasePrice(BicycleRepository bicycleRepository) {
        Bicycle bicycle = new Bicycle(789L);
        DynamodbAttributes relatedItemsAttributes = new DynamodbAttributes();
        relatedItemsAttributes.putNumber(":p", 15F);
        bicycle.setUpdate(new Update("SET Price = Price - :p", relatedItemsAttributes));
        bicycleRepository.update(bicycle);

        /*
        {
    ":r5": { "S": "Very happy with my purchase" },
    ":r3": {
        "L": [
            { "S": "Just OK - not that great" }
        ]
    }
}
         */
    }

    //Appending Elements To a List
    private static void AppendToList(BicycleRepository bicycleRepository) {
        Map<String, String> map = new HashMap<>();
        map.put("#ri", "RelatedItems");

        List<String> list = new ArrayList<>();
        list.add("Screwdriver");
        list.add("Hacksaw");

        Bicycle bicycle = new Bicycle(789L);
        DynamodbAttributes relatedItemsAttributes = new DynamodbAttributes();
        relatedItemsAttributes.putList(":vals", list, DynamoTypeEnum.string);
        bicycle.setUpdate(new Update("SET #ri = list_append(#ri, :vals)", relatedItemsAttributes, map));
        bicycleRepository.update(bicycle);

        /*
       {
    ":vals": {
        "L": [
            { "S": "Screwdriver" },
            {"S": "Hacksaw" }
        ]
    }
}
         */
    }

    private static void AddAtListBeginning(BicycleRepository bicycleRepository) {
        Map<String, String> map = new HashMap<>();
        map.put("#ri", "RelatedItems");

        List<String> list = new ArrayList<>();
        list.add("Chisel");

        Bicycle bicycle = new Bicycle(789L);
        DynamodbAttributes relatedItemsAttributes = new DynamodbAttributes();
        relatedItemsAttributes.putList(":vals", list, DynamoTypeEnum.string);
        bicycle.setUpdate(new Update("SET #ri = list_append(:vals, #ri)", relatedItemsAttributes, map));
        bicycleRepository.update(bicycle);

        /*
       {
    ":vals": {
        "L": [
            { "S": "Chisel" }
        ]
    }
}
         */
    }

    private static void RemoveAttributes(BicycleRepository bicycleRepository) {
        Bicycle bicycle = new Bicycle(789L);
        bicycle.setUpdate(new Update("REMOVE Brand, InStock, QuantityOnHand"));
        bicycleRepository.update(bicycle);
    }

    //Removing Elements From a List
    private static void RemoveListElements(BicycleRepository bicycleRepository) {
        //initialize data
        AddListAndMapTOItem(bicycleRepository);
        AppendToList(bicycleRepository);
        AddAtListBeginning(bicycleRepository);

        Bicycle bicycle = new Bicycle(789L);
        bicycle.setUpdate(new Update("REMOVE RelatedItems[1], RelatedItems[2]"));
        bicycleRepository.update(bicycle);
    }


    private static void IncrementAttributeValue(BicycleRepository bicycleRepository) {
        Bicycle bicycle = new Bicycle(789L);
        DynamodbAttributes relatedItemsAttributes = new DynamodbAttributes();
        relatedItemsAttributes.putNumber(":q", 5);
        bicycle.setUpdate(new Update("ADD QuantityOnHand :q", relatedItemsAttributes));
        bicycleRepository.update(bicycle);
        /*
        {":q": {"N": "5"}}
         */
    }

}
