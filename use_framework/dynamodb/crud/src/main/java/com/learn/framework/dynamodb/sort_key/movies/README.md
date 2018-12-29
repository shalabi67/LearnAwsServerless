#Movies example
This example shows how to create a movie. 
The movie objects include MovieInformation object.
this example had been taken from:
https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/GettingStarted.Python.02.html

##Run 
###create database on live
run these commands from sam directory:
sam package --template-file movies.yaml --output-template-file movies_packaged.yaml --s3-bucket shalabi-sam 
sam deploy --template-file movies_packaged.yaml --stack-name learn-movies --capabilities CAPABILITY_IAM

###Create database locally
####Start DynamoDB Local in a Docker container. 
docker run -d -p 8000:8000 amazon/dynamodb-local

####create movies table on local
aws dynamodb create-table --table-name learn-movies  --attribute-definitions  AttributeName=year,AttributeType=N  AttributeName=title,AttributeType=S --key-schema AttributeName=year,KeyType=HASH AttributeName=title,KeyType=RANG --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5 --endpoint-url http://192.168.99.100:8000

##delete table
aws dynamodb delete-table --table-name learn-movies --endpoint-url http://192.168.99.100:8000

####Put item
aws dynamodb put-item --table-name learn-movies --item file://item.json --endpoint-url http://192.168.99.100:8000

####set enviroment variuables
set LOCAL_DYNAMODB_URL=http://192.168.99.100:8000 
you can set it through intilij project run configuration. 

#### set maven profile to local


##run live
make sure the live profile is selected


