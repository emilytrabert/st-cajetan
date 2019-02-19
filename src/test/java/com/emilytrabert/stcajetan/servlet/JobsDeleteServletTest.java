package com.emilytrabert.stcajetan.servlet;

import static com.emilytrabert.stcajetan.Constants.JOB_PATH;
import static com.emilytrabert.stcajetan.servlet.JobsDeleteServlet.JOB_ID_ATTRIBUTE_NAME;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;

import com.emilytrabert.stcajetan.Job;
import com.emilytrabert.stcajetan.gateway.DynamoDBGateway;

public class JobsDeleteServletTest {

    private DynamoDBGateway gateway;
    private JobsDeleteServlet classUnderTest;

    private HttpServletRequest request;
    private HttpServletResponse response;

    private List<Job> results;

    @Before
    public void setup() {
        gateway = mock(DynamoDBGateway.class);
        classUnderTest = new JobsDeleteServlet(gateway);

        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }

    @Test
    public void testPost() throws ServletException, IOException {
        String testId = "test";
        when(gateway.getAll()).thenReturn(results);
        when(request.getParameterMap()).thenReturn(new HashMap<String, String[]>() {
            {
                put(JOB_ID_ATTRIBUTE_NAME, new String[] { testId });
            }
        });
        String contextPath = "localhost:5000";
        when(request.getContextPath()).thenReturn(contextPath);

        classUnderTest.doPost(request, response);

        // Verify doPost actions
        verify(gateway).delete(testId);

        // Verify doGet is called after
        verify(response).sendRedirect(contextPath + JOB_PATH);
    }
}
