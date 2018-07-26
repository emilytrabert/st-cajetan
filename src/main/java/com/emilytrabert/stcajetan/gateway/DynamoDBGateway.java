package com.emilytrabert.stcajetan.gateway;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.emilytrabert.stcajetan.Job;
import com.emilytrabert.stcajetan.JobStatus;

public class DynamoDBGateway {

    private final AmazonDynamoDB client;
    private final DynamoDBMapper mapper;

    public DynamoDBGateway() {
        this.client = AmazonDynamoDBClientBuilder.standard().build();
        this.mapper = new DynamoDBMapper(client);
    }

    public Optional<Job> getById(String id) {
        return Optional.ofNullable(mapper.load(Job.class, id));
    }

    public List<Job> getAll() {
        Map<String, AttributeValue> expressionMap = new HashMap<>();
        expressionMap.put(":status", new AttributeValue().withS(JobStatus.INTERVIEWING.name()));
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withFilterExpression("jobStatus = :status")
                .withExpressionAttributeValues(expressionMap);
        PaginatedScanList<Job> results = mapper.scan(Job.class, scanExpression);
        return results;
    }
}
