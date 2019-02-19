package com.emilytrabert.stcajetan.servlet;

import static com.emilytrabert.stcajetan.Constants.JOB_PATH;
import static com.emilytrabert.stcajetan.servlet.JobsServlet.LISTING_URL_ATTRIBUTE_NAME;
import static com.emilytrabert.stcajetan.servlet.JobsServlet.NOTES_ATTRIBUTE_NAME;
import static com.emilytrabert.stcajetan.servlet.JobsUpdateServlet.JOB_ID_ATTRIBUTE_NAME;
import static com.emilytrabert.stcajetan.servlet.JobsUpdateServlet.JOB_STATUS_ATTRIBUTE_NAME;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;

import com.emilytrabert.stcajetan.Job;
import com.emilytrabert.stcajetan.JobStatus;
import com.emilytrabert.stcajetan.gateway.DynamoDBGateway;

public class JobsUpdateServletTest {

    private DynamoDBGateway gateway;
    private JobsUpdateServlet classUnderTest;

    private HttpServletRequest request;
    private HttpServletResponse response;

    @Before
    public void setup() {
        gateway = mock(DynamoDBGateway.class);
        classUnderTest = new JobsUpdateServlet(gateway);

        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }

    @Test
    public void testPost() throws ServletException, IOException {
        when(request.getParameterMap()).thenReturn(new HashMap<String, String[]>() {
            {
                put(JOB_ID_ATTRIBUTE_NAME, new String[] { "test" });
                put(JOB_STATUS_ATTRIBUTE_NAME, new String[] { JobStatus.INTERVIEWING.toString() });
                put(LISTING_URL_ATTRIBUTE_NAME, new String[] { "google.com" });
                put(NOTES_ATTRIBUTE_NAME, new String[] { "some notes" });
            }
        });
        String contextPath = "localhost:5000";
        when(request.getContextPath()).thenReturn(contextPath);

        classUnderTest.doPost(request, response);

        // Verify doPost actions
        verify(gateway).save(any(Job.class));

        // Verify doGet is called after
        verify(response).sendRedirect(contextPath + JOB_PATH);
    }
}
