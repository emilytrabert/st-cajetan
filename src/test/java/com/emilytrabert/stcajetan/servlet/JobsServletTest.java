package com.emilytrabert.stcajetan.servlet;

import static com.emilytrabert.stcajetan.Constants.JOB_PATH;
import static com.emilytrabert.stcajetan.servlet.JobsServlet.JOBS_ATTRIBUTE_NAME;
import static com.emilytrabert.stcajetan.servlet.JobsServlet.JOBS_JSP_PATH;
import static com.emilytrabert.stcajetan.servlet.JobsServlet.JOB_STATUSES_ATTRIBUTE_NAME;
import static com.emilytrabert.stcajetan.servlet.JobsServlet.LISTING_URL_ATTRIBUTE_NAME;
import static com.emilytrabert.stcajetan.servlet.JobsServlet.NOTES_ATTRIBUTE_NAME;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;

import com.emilytrabert.stcajetan.Job;
import com.emilytrabert.stcajetan.JobStatus;
import com.emilytrabert.stcajetan.gateway.DynamoDBGateway;

public class JobsServletTest {

    private DynamoDBGateway gateway;
    private JobsServlet classUnderTest;

    private HttpServletRequest request;
    private HttpServletResponse response;

    private List<Job> results;
    private RequestDispatcher dispatcher;

    @Before
    public void setup() {
        gateway = mock(DynamoDBGateway.class);
        classUnderTest = new JobsServlet(gateway);

        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        dispatcher = mock(RequestDispatcher.class);
    }

    @Test
    public void testGet() throws ServletException, IOException {
        when(gateway.getAll()).thenReturn(results);
        when(request.getRequestDispatcher(JOBS_JSP_PATH)).thenReturn(dispatcher);

        classUnderTest.doGet(request, response);

        verify(request).setAttribute(JOBS_ATTRIBUTE_NAME, results);
        verify(request).setAttribute(JOB_STATUSES_ATTRIBUTE_NAME, JobStatus.values());
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testPost() throws ServletException, IOException {
        when(gateway.getAll()).thenReturn(results);
        when(request.getRequestDispatcher(JOBS_JSP_PATH)).thenReturn(dispatcher);
        when(request.getParameterMap()).thenReturn(new HashMap<String, String[]>() {
            {
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
