package com.emilytrabert.stcajetan.gateway;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.emilytrabert.stcajetan.Job;
import com.emilytrabert.stcajetan.JobStatus;

public class DynamoDBGatewayTest {

    private static final String TEST_ID = "test";
    private static final JobStatus TEST_STATUS = JobStatus.INTERVIEWING;

    private DynamoDBMapper mapper;
    private DynamoDBGateway classUnderTest;
    private Job testJob;

    @Before
    public void setup() {
        mapper = mock(DynamoDBMapper.class);
        classUnderTest = new DynamoDBGateway();
        testJob = new Job();
        testJob.setId(TEST_ID);
        testJob.setJobStatus(TEST_STATUS);
    }

    @Test
    public void testGetById() {
        when(mapper.load(Job.class, TEST_ID)).thenReturn(testJob);
        Optional<Job> result = classUnderTest.getById(TEST_ID);
        assertTrue(result.isPresent());
        Job job = result.get();
        assertEquals(TEST_STATUS, job.getJobStatus());
    }

}
