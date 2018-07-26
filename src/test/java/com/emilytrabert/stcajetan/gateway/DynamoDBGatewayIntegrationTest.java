package com.emilytrabert.stcajetan.gateway;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.emilytrabert.stcajetan.Job;
import com.emilytrabert.stcajetan.JobStatus;

public class DynamoDBGatewayIntegrationTest {

    private DynamoDBGateway classUnderTest;

    @Before
    public void setup() {
        classUnderTest = new DynamoDBGateway();
    }

    @Test
    public void testGetById() {
        Optional<Job> result = classUnderTest.getById("test");
        assertTrue(result.isPresent());
        Job job = result.get();
        assertEquals(JobStatus.INTERVIEWING, job.getJobStatus());
    }

    @Test
    public void testGetAll() {
        List<Job> results = classUnderTest.getAll();
        assertEquals(1, results.size());
        Job item = results.get(0);
        assertEquals("test", item.getId());
        assertEquals(JobStatus.INTERVIEWING, item.getJobStatus());
    }

}
