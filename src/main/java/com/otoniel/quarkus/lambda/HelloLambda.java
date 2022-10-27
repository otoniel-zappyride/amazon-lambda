package com.otoniel.quarkus.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;

import javax.inject.Inject;

public class HelloLambda implements RequestHandler<HelloRequest, String> {

    @Inject
    HelloGreeter greeter;

    @Override
    public String handleRequest(HelloRequest request, final Context context) {

        S3Client s3 = S3Client.builder().region(Region.of("us-east-1")).httpClient(UrlConnectionHttpClient.builder().build()).build();

        System.out.println(s3.listObjects(ListObjectsRequest.builder().bucket("tests2").build()));

        return greeter.greet(request.firstName, request.lastName);
    }
}
