#Sam Templates 
##Create simple table
sam package --template-file simple_table.yaml --output-template-file packaged.yaml --s3-bucket shalabi-sam
sam deploy --template-file packaged.yaml --stack-name sam-sim-table --capabilities CAPABILITY_IAM

##create movies table
sam package --template-file normal_table.yaml --output-template-file packaged.yaml --s3-bucket shalabi-sam
sam deploy --template-file packaged.yaml --stack-name sam-table --capabilities CAPABILITY_IAM