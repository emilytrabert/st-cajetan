package com.emilytrabert.stcajetan.servlet;

import static com.emilytrabert.stcajetan.servlet.JobsServlet.JOBS_ATTRIBUTE_NAME;
import static com.emilytrabert.stcajetan.servlet.JobsServlet.JOBS_JSP_PATH;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;

import com.emilytrabert.stcajetan.Job;
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
        verify(dispatcher).forward(request, response);
    }

}
