#Image thumbnail service
Lab wil be found in: https://amazon-run.qwiklabs.com/catalog_lab/222

##package
sam package --output-template-file packaged.yaml --s3-bucket shalabi-sam

##deploy
sam deploy --template-file packaged.yaml --stack-name sam-image-resize --capabilities CAPABILITY_IAM
