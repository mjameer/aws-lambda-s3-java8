package com.mj;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3Object;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import java.io.InputStream;

public class LambdaHandler implements RequestHandler<S3Event,String> {

    private static final AmazonS3 s3client = AmazonS3Client.builder()
            .withCredentials(new DefaultAWSCredentialsProviderChain())
            .build();

    @Override
    public String handleRequest(S3Event input, Context context) {
        final LambdaLogger logger = context.getLogger();

        //check if are getting any record
        if(input.getRecords().isEmpty()){
            logger.log("No records found");
            return "No records found";
        }
        //process the records
        for(S3EventNotification.S3EventNotificationRecord record: input.getRecords()){
            String bucketName = record.getS3().getBucket().getName();
            String fileName = record.getS3().getObject().getKey();
            context.getLogger().log("BucketName ::: " + bucketName );
            context.getLogger().log("fileName ::: " + fileName );

            //1. we create S3 client
            //2. invoking GetObject
            //3. processing the InputStream from S3

            S3Object s3Object = s3client.getObject(bucketName, fileName);
            InputStream inputStream = s3Object.getObjectContent();
            //processing CSV - open CSV, apache CSV

            try{
                final BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                br.lines().skip(1).forEach(line -> logger.log(line + "\n"));

                // Alternate way using Commons IO
                //String content = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
                //context.getLogger().log("file content ::: " + content );

            } catch (Exception e){
                logger.log("Error occurred in Lambda:" + e.getMessage());
                return "Error while reading file from S3 :::" +e.getMessage();
            }

        }
        return "Successfully read file from s3 bucket";
    }
}
