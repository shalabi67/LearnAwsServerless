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