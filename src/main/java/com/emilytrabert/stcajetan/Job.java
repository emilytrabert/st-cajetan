package com.emilytrabert.stcajetan;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@DynamoDBTable(tableName = "job-apps")
public class Job {

    private String id;
    private JobStatus jobStatus;
    @Getter
    private String listingUrl;
    @Getter
    private String notes;

    @DynamoDBHashKey
    public String getId() {
        return id;
    }

    @DynamoDBTypeConvertedEnum
    public JobStatus getJobStatus() {
        return jobStatus;
    }
}
