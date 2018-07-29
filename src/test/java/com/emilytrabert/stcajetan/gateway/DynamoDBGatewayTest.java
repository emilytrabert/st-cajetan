package com.emilytrabert.stcajetan.gateway;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
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
        classUnderTest = new DynamoDBGateway(mapper);
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

    @Test
    public void testGetAll() {
        PaginatedScanList<Job> mapperResults = mock(PaginatedScanList.class);
        when(mapperResults.size()).thenReturn(1);
        when(mapperResults.get(0)).thenReturn(testJob);
        when(mapper.scan(eq(Job.class), any(DynamoDBScanExpression.class))).thenReturn(mapperResults);

        List<Job> results = classUnderTest.getAll();

        assertEquals(1, results.size());
        Job item = results.get(0);
        assertEquals("test", item.getId());
        assertEquals(JobStatus.INTERVIEWING, item.getJobStatus());
    }
}
