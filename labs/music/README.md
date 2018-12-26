#Music
##overview
This lab shows how to create a table.
This project represents "Serverless Web Apps using Amazon DynamoDB" quest. https://amazon-run.qwiklabs.com/quests/21?catalog_rank=%7B%22rank%22%3A1%2C%22num_filters%22%3A0%2C%22has_search%22%3Atrue%7D&search_id=1776913
This lab teaches you about Amazon DynamoDB and walks you through how to create, query, view and delete a table in the AWS Management Console. 
For a demonstration, go to: https://www.youtube.com/watch?v=ujWV3-m1pLo 

##Build
###Package
sam package --output-template-file packaged.yaml --s3-bucket shalabi-sam

###Deploy
sam deploy --template-file packaged.yaml --stack-name sam-music --capabilities CAPABILITY_IAM


