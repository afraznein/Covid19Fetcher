package com.app.covidfetcher.service;

import com.app.covidfetcher.constants.LambdaAWSConstants;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sns.model.SnsException;


public class EmailService {

    private static SnsClient createSNSClient(){
        return SnsClient.builder()
                .region(Region.US_EAST_2)
                .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                .build();
    }

    private static void sendEmail(String message, String topicArn) {
        try {
            PublishRequest request = PublishRequest.builder()
                    .message(message)
                    .topicArn(topicArn)
                    .build();

            PublishResponse result = createSNSClient().publish(request);
            System.out.println(result.messageId() + " Message sent. Status is " + result.sdkHttpResponse().statusCode());

        } catch (SnsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
        }
    }

    public static void sendCovid19Email(String message){
        sendEmail(message, LambdaAWSConstants.COVID_19_TOPIC_ARN);
    }
}
