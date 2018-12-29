#FAQ-API: Introduction to API gateway
Lab wil be found in: https://amazon-run.qwiklabs.com/catalog_lab/236


##package
sam package --output-template-file packaged.yaml --s3-bucket shalabi-sam

##deploy
sam deploy --template-file packaged.yaml --stack-name sam-faq --capabilities CAPABILITY_IAM
