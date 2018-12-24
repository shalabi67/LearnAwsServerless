#Environment sample
This example shows how you can use environment variables with your lambda. this function is configured using SAM templates.

##Build
mvn package

##create s3 bucket(optional) needed only if bucket not exists
aws s3 mb s3://BUCKET_NAME
aws s3 mb s3://shalabi-sam

##copy jar file to s3
sam package \
    --template-file template.yaml \
    --output-template-file packaged.yaml \
    --s3-bucket REPLACE_THIS_WITH_YOUR_S3_BUCKET_NAME
    
sam package \
    --template-file template.yaml \
    --output-template-file packaged.yaml \
    --s3-bucket shalabi-sam
    
sam package --template-file template.yaml --output-template-file packaged.yaml --s3-bucket shalabi-sam   


#Deploy solution to AWS
sam deploy \
    --template-file packaged.yaml \
    --stack-name env-app \
    --capabilities CAPABILITY_IAM
    
sam deploy --template-file packaged.yaml --stack-name env-app --capabilities CAPABILITY_IAM