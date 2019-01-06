#CLI
this example will show how to use CLI examples show in aws documentation. it will create all needed tables.
to fill these tables with needed data you need to run java files defined in crud module.

##Run 
https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/SampleData.html
###create database on live
run these commands from sam directory:
sam package --template-file db.yaml --output-template-file packaged.yaml --s3-bucket shalabi-sam 
sam deploy --template-file packaged.yaml --stack-name learn-dynamodb-cli --capabilities CAPABILITY_IAM

###fill data
aws dynamodb batch-write-item --request-items file://ProductCatalog.json
aws dynamodb batch-write-item --request-items file://Forum.json
aws dynamodb batch-write-item --request-items file://Thread.json
aws dynamodb batch-write-item --request-items file://Reply.json

##Query Examples
###Example 1: Query the Thread table for a particular ForumName
aws dynamodb query \
    --table-name learn-thread \
    --key-condition-expression "ForumName = :name" \
    --expression-attribute-values  '{":name":{"S":"Amazon DynamoDB"}}'
    
aws dynamodb query --table-name learn-thread --key-condition-expression "ForumName = :name" --expression-attribute-values  "{\":name\":{\"S\":\"Amazon DynamoDB\"}}"

###Example 2: Query the Thread table for a particular ForumName (partition key), and a given Subject (sort key)
aws dynamodb query \
    --table-name learn-thread \
    --key-condition-expression "ForumName = :name and Subject = :sub" \
    --expression-attribute-values  file://files/query2_values.json
    
aws dynamodb query --table-name learn-thread --key-condition-expression "ForumName = :name and Subject = :sub" --expression-attribute-values  file://files/query2_values.json

###Example 3: Query the Reply table for a particular Id (partition key), but return only those items whose ReplyDateTime (sort key) begins with certain characters
aws dynamodb query \
    --table-name learn-reply \
    --key-condition-expression "Id = :id and begins_with(ReplyDateTime, :dt)" \
    --expression-attribute-values  file://files/query3_values.json

aws dynamodb query --table-name learn-reply --key-condition-expression "Id = :id and begins_with(ReplyDateTime, :dt)" --expression-attribute-values  file://files/query3_values.json
    
###Example 4
aws dynamodb query \
    --table-name learn-thread \
    --key-condition-expression "ForumName = :fn" \
    --filter-expression "#v >= :num" \
    --expression-attribute-names '{"#v": "Views"}' \
    --expression-attribute-values file://files/query4_values.json

aws dynamodb query --table-name learn-thread --key-condition-expression "ForumName = :fn" --filter-expression "#v >= :num" --expression-attribute-names "{\"#v\": \"Views\"}" --expression-attribute-values file://files/query4_values.json   

###Example 5: show how pagination works. notice the use of --debug

aws dynamodb query --table-name learn-movies \
    --projection-expression "title" \
    --key-condition-expression "#y = :yyyy" \
    --expression-attribute-names '{"#y":"year"}' \
    --expression-attribute-values '{":yyyy":{"N":"1993"}}' \
    --page-size 5 \
    --debug
    
aws dynamodb query --table-name learn-movies --projection-expression "title" --key-condition-expression "#y = :yyyy" --expression-attribute-names "{\"#y\":\"year\"}" --expression-attribute-values "{\":yyyy\":{\"N\":\"1993\"}}" --page-size 5 --debug


##Examples ProductCatalog
###Projection
aws dynamodb get-item \
    --table-name learn-product-catalog \
    --key file://files/key.json \
    --projection-expression "Description, RelatedItems[0], ProductReviews.FiveStar" 

aws dynamodb get-item --table-name learn-product-catalog --key file://files/key.json --projection-expression "Description, RelatedItems[0], ProductReviews.FiveStar"
 
 ###Expression attribute names
 ####Example 1
 aws dynamodb get-item \
     --table-name learn-product-catalog \
     --key '{"Id":{"N":"123"}}' \
     --projection-expression "#sw" \
     --expression-attribute-names '{"#sw":"Safety.Warning"}'

aws dynamodb get-item --table-name learn-product-catalog --key "{\"Id\":{\"N\":\"123\"}}" --projection-expression "#sw" --expression-attribute-names "{\"#sw\":\"Safety.Warning\"}"

####Example 2 
 aws dynamodb get-item \
     --table-name learn-product-catalog \
     --key '{"Id":{"N":"123"}}' \
     --projection-expression "#pr.#1star"  \
     --expression-attribute-names '{"#pr":"ProductReviews", "#1star":"OneStar"}'
     
     
aws dynamodb  get-item --table-name learn-product-catalog --key "{\"Id\":{\"N\":\"123\"}}" --projection-expression "#pr.#1star" --expression-attribute-names "{\"#pr\":\"ProductReviews\", \"#1star\":\"OneStar\"}"

####Example 3
aws dynamodb get-item \
    --table-name learn-product-catalog \
    --key '{"Id":{"N":"123"}}' \
    --projection-expression "#pr.FiveStar, #pr.ThreeStar, #pr.OneStar" \
    --expression-attribute-names '{"#pr":"ProductReviews"}'

aws dynamodb get-item  --table-name learn-product-catalog --key "{\"Id\":{\"N\":\"123\"}}" --projection-expression "#pr.FiveStar, #pr.ThreeStar, #pr.OneStar" --expression-attribute-names "{\"#pr\":\"ProductReviews\"}"

##update 
###Set
####Example 1
aws dynamodb update-item \
    --table-name learn-product-catalog \
    --key '{"Id":{"N":"789"}}' \
    --update-expression "SET ProductCategory = :c, Price = :p" \
    --expression-attribute-values file://values.json \
    --return-values ALL_NEW

aws dynamodb update-item --table-name learn-product-catalog --key "{\"Id\":{\"N\":\"789\"}}" --update-expression "SET ProductCategory = :c, Price = :p" --expression-attribute-values "{\":c\": { \"S\": \"Hardware\" },\":p\": { \"N\": \"60\" }}" --return-values ALL_NEW

####Example 2: Add a new list and a new map
aws dynamodb update-item \
    --table-name learn-product-catalog \
    --key '{"Id":{"N":"789"}}' \
    --update-expression "SET RelatedItems = :ri, ProductReviews = :pr" \
    --expression-attribute-values file://values.json \
    --return-values ALL_NEW
    
aws dynamodb update-item --table-name learn-product-catalog --key "{\"Id\":{\"N\":\"789\"}}" --update-expression "SET RelatedItems = :ri, ProductReviews = :pr" --expression-attribute-values "{\":ri\": {\"L\": [{ \"S\": \"Hammer\" }]},\":pr\": {\"M\": {\"FiveStar\": {\"L\": [{ \"S\": \"Best product ever!\" }]}}}}" --return-values ALL_NEW
    
####Example 3: Adding Elements To a List.
run example 2 before you run example 3. both examples are related.
aws dynamodb update-item \
    --table-name learn-product-catalog \
    --key '{"Id":{"N":"789"}}' \
    --update-expression "SET RelatedItems[1] = :ri" \
    --expression-attribute-values file://files/nails.json \
    --return-values ALL_NEW

aws dynamodb update-item --table-name learn-product-catalog --key "{\"Id\":{\"N\":\"789\"}}" --update-expression "SET RelatedItems[1] = :ri" --expression-attribute-values file://files/nails.json --return-values ALL_NEW

####Example 4:Adding Nested Map Attributes
run example 2 before you run example 4. both examples are related.
aws dynamodb update-item \
    --table-name learn-product-catalog \
    --key '{"Id":{"N":"789"}}' \
    --update-expression "SET #pr.#5star[1] = :r5, #pr.#3star = :r3" \
    --expression-attribute-names file://files/set4_names.json \
    --expression-attribute-values file://files/set4_values.json \
    --return-values ALL_NEW

aws dynamodb update-item --table-name learn-product-catalog --key "{\"Id\":{\"N\":\"789\"}}" --update-expression "SET #pr.#5star[1] = :r5, #pr.#3star = :r3" --expression-attribute-names file://files/set4_names.json --expression-attribute-values file://files/set4_values.json --return-values ALL_NEW

####Example 5: Atomic increment and decrement
aws dynamodb update-item \
    --table-name learn-product-catalog \
    --key '{"Id":{"N":"789"}}' \
    --update-expression "SET Price = Price - :p" \
    --expression-attribute-values '{":p": {"N":"15"}}' \
    --return-values ALL_NEW
    
aws dynamodb update-item --table-name learn-product-catalog --key "{\"Id\":{\"N\":\"789\"}}" --update-expression "SET Price = Price - :p" --expression-attribute-values "{\":p\": {\"N\":\"15\"}}" --return-values ALL_NEW

####Example 6: Appending Elements To a List
aws dynamodb update-item \
    --table-name learn-product-catalog \
    --key '{"Id":{"N":"789"}}' \
    --update-expression "SET #ri = list_append(#ri, :vals)" \
    --expression-attribute-names '{"#ri": "RelatedItems"}' \
    --expression-attribute-values file://files/set6_values.json  \
    --return-values ALL_NEW

aws dynamodb update-item --table-name learn-product-catalog --key "{\"Id\":{\"N\":\"789\"}}" --update-expression "SET #ri = list_append(#ri, :vals)" --expression-attribute-names "{\"#ri\": \"RelatedItems\"}" --expression-attribute-values file://files/set6_values.json  --return-values ALL_NEW

####Example 7: add at the beginning of a list
aws dynamodb update-item \
    --table-name learn-product-catalog \
    --key '{"Id":{"N":"789"}}' \
    --update-expression "SET #ri = list_append(:vals, #ri)" \
    --expression-attribute-names '{"#ri": "RelatedItems"}' \
    --expression-attribute-values '{":vals": {"L": [ { "S": "Chisel" }]}}' \
    --return-values ALL_NEW

aws dynamodb update-item --table-name learn-product-catalog --key "{\"Id\":{\"N\":\"789\"}}" --update-expression "SET #ri = list_append(:vals, #ri)" --expression-attribute-names "{\"#ri\": \"RelatedItems\"}" --expression-attribute-values "{\":vals\": {\"L\": [ { \"S\": \"Chisel\" }]}}" --return-values ALL_NEW


###REMOVE
####Example 1: Remove some attributes from an item.
aws dynamodb update-item \
    --table-name learn-product-catalog \
    --key '{"Id":{"N":"789"}}' \
    --update-expression "REMOVE Brand, InStock, QuantityOnHand" \
    --return-values ALL_NEW

aws dynamodb update-item --table-name learn-product-catalog --key "{\"Id\":{\"N\":\"789\"}}" --update-expression "REMOVE Brand, InStock, QuantityOnHand" --return-values ALL_NEW
    

##challenge
## return all of the ProductCatalog items that are available in Black and cost 500 or less
aws dynamodb scan \
    --table-name learn-product-catalog \
    --filter-expression "contains(Color, :c) and Price <= :p" \
    --expression-attribute-values file://files/values.json
    
aws dynamodb scan --table-name learn-product-catalog --filter-expression "contains(Color, :c) and Price <= :p" --expression-attribute-values file://files/values.json

## return all of the ProductCatalog items where ProductCategory is either "Sporting Goods" or "Gardening Supplies" and The Price is between 500 and 600.
aws dynamodb scan --table-name learn-product-catalog --filter-expression "ProductCategory IN(\"Sporting Goods\", \"Gardening Supplies\") and Price BETWEEN 500 and 600" 
--expression-attribute-values file://files/values.json
