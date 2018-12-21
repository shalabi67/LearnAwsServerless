#Orders example
This example had been written by Shalabi. The goal is to provide a complete working example without any hacks 
that uses sam template with lamdba function, API gate way, and dynamodb.

##SAM
###Build
mvn package
sam package     --template-file template.yaml     --output-template-file packaged.yaml     --s3-bucket shalabi-sam
sam deploy     --template-file packaged.yaml     --stack-name sam-orders     --capabilities CAPABILITY_NAMED_IAM
sam deploy \
    --template-file packaged.yaml \
    --stack-name sam-orders \
    --capabilities CAPABILITY_NAMED_IAM
    
    
###Run locally
####dynamodb
dock#er run -d -p 8000:8000 amazon/dynamodb-local
aws dynamodb list-tables --endpoint-url http://localhost:8000

####sam
sam local start-api -d 5858 
http://127.0.0.1:3000/orders 

##Data
###OrderRequest
{
    "customerId":"123",
    "preTaxAmount":200,
    "postTaxAmount": 210
}