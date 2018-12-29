#Music
##overview
This lab shows how to create a table.
This project represents "Serverless Web Apps using Amazon DynamoDB" quest. https://amazon-run.qwiklabs.com/quests/21?catalog_rank=%7B%22rank%22%3A1%2C%22num_filters%22%3A0%2C%22has_search%22%3Atrue%7D&search_id=1776913
This lab teaches you about Amazon DynamoDB and walks you through how to create, query, view and delete a table in the AWS Management Console. 
For a demonstration, go to: https://www.youtube.com/watch?v=ujWV3-m1pLo 

##Live Build
###Build
mvn package -Plive

###Package
sam package --output-template-file packaged.yaml --s3-bucket shalabi-sam

###Deploy
sam deploy --template-file packaged.yaml --stack-name sam-super-mission --capabilities CAPABILITY_NAMED_IAM


##Local
###Dynamodb
####Start DynamoDB Local in a Docker container. 
docker run -d -p 8000:8000 amazon/dynamodb-local

####create questions table
aws dynamodb create-table --table-name lab-super-missions  --attribute-definitions  AttributeName=superHero,AttributeType=S --key-schema AttributeName=superHero,KeyType=HASH --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5 --endpoint-url http://192.168.99.100:8000

###list tables
aws dynamodb list-tables --endpoint-url http://192.168.99.100:8000

###get table schema
aws dynamodb describe-table --table-name lab-super-missions  --endpoint-url http://192.168.99.100:8000

###Build
mvn package -Plocal

###starting sam
sam local start-api


###start sam for debug
sam local start-api -d 5858
