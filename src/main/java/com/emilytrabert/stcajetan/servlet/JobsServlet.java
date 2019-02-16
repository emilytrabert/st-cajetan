package com.emilytrabert.stcajetan.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emilytrabert.stcajetan.Job;
import com.emilytrabert.stcajetan.JobStatus;
import com.emilytrabert.stcajetan.gateway.DynamoDBGateway;

@WebServlet("/jobs")
public class JobsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    static final String JOBS_ATTRIBUTE_NAME = "jobs";
    static final String LISTING_URL_ATTRIBUTE_NAME = "listingUrl";
    static final String NOTES_ATTRIBUTE_NAME = "notes";
    static final String JOBS_JSP_PATH = "/WEB-INF/jobs.jsp";

    private final DynamoDBGateway gateway;

    public JobsServlet() {
        this.gateway = new DynamoDBGateway();
    }

    JobsServlet(DynamoDBGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Job> jobs = gateway.getAll();
        request.setAttribute(JOBS_ATTRIBUTE_NAME, jobs);
        request.getRequestDispatcher(JOBS_JSP_PATH).forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> params = request.getParameterMap();
        Job job = new Job();
        job.setId(UUID.randomUUID().toString());
        job.setJobStatus(JobStatus.SAVED);
        job.setListingUrl(params.get(LISTING_URL_ATTRIBUTE_NAME)[0]);
        job.setNotes(params.get(NOTES_ATTRIBUTE_NAME)[0]);
        gateway.save(job);
        doGet(request, response);
    }
}
