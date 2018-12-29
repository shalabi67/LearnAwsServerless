#FAQ-API: Introduction to API gateway
Lab wil be found in: https://amazon-run.qwiklabs.com/catalog_lab/236


##package
mvn package -Plive
sam package --output-template-file packaged.yaml --s3-bucket shalabi-sam

##deploy
sam deploy --template-file packaged.yaml --stack-name sam-faq-framework --capabilities CAPABILITY_IAM


##Local environment
###Dynamodb
####Start DynamoDB Local in a Docker container. 
docker run -d -p 8000:8000 amazon/dynamodb-local

####create questions table
aws dynamodb create-table --table-name questions  --attribute-definitions  AttributeName=id,AttributeType=S --key-schema AttributeName=id,KeyType=HASH --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5 --endpoint-url http://192.168.99.100:8000


###Package
mvn package -Plocal

###Sam local for FAQ
Start the SAM local API.
 - On a Mac: `sam local start-api --env-vars src/test/resources/test_environment_mac.json`.
 - On Windows: `sam local start-api --env-vars src/test/resources/test_environment_windows.json`
 - On Windows7: `sam local start-api --env-vars src/test/resources/test_environment_windows7.json`
 - On Linux: `sam local start-api --env-vars src/test/resources/test_environment_linux.json`
 


##profiles
check which profile is active
mvn help:active-profiles 
mvn help:active-profiles -Dframework.env=live