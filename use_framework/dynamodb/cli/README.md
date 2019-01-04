#CLI
this example will show how to use CLI examples show in aws documentation. it will create all needed tables.
to fill these tables with needed data you need to run java files defined in crud module.

##Run 
###create database on live
run these commands from sam directory:
sam package --template-file db.yaml --output-template-file packaged.yaml --s3-bucket shalabi-sam 
sam deploy --template-file packaged.yaml --stack-name learn-dynamodb-cli --capabilities CAPABILITY_IAM

###fill data
aws dynamodb batch-write-item --request-items file://ProductCatalog.json

##Examples ProductCatalog
###Projection
aws dynamodb get-item \
    --table-name learn-product-catalog \
    --key file://files/key.json \
    --projection-expression "Description, RelatedItems[0], ProductReviews.FiveStar"

aws dynamodb get-item --table-name learn-product-catalog --key file://files/key.json --projection-expression "Description, RelatedItems[0], ProductReviews.FiveStar"
 
 ###Expression attribute names
 aws dynamodb get-item \
     --table-name learn-product-catalog \
     --key '{"Id":{"N":"123"}}' \
     --projection-expression "#pr.#1star"  \
     --expression-attribute-names '{"#pr":"ProductReviews", "#1star":"OneStar"}'
     
     
aws dynamodb  get-item --table-name learn-product-catalog --key "{\"Id\":{\"N\":\"123\"}}" --projection-expression "#pr.#1star" --expression-attribute-names "{\"#pr\":\"ProductReviews\", \"#1star\":\"OneStar\"}"


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
