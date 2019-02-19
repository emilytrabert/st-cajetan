package com.emilytrabert.stcajetan.servlet;

import static com.emilytrabert.stcajetan.Constants.JOB_PATH;
import static com.emilytrabert.stcajetan.Constants.JOB_UPDATE_PATH;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emilytrabert.stcajetan.Job;
import com.emilytrabert.stcajetan.JobStatus;
import com.emilytrabert.stcajetan.gateway.DynamoDBGateway;

@WebServlet(JOB_UPDATE_PATH)
public class JobsUpdateServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    static final String JOB_ID_ATTRIBUTE_NAME = "jobId";
    static final String JOB_STATUS_ATTRIBUTE_NAME = "jobStatus";
    static final String LISTING_URL_ATTRIBUTE_NAME = "listingUrl";
    static final String NOTES_ATTRIBUTE_NAME = "notes";

    private final DynamoDBGateway gateway;

    public JobsUpdateServlet() {
        this.gateway = new DynamoDBGateway();
    }

    JobsUpdateServlet(DynamoDBGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> params = request.getParameterMap();
        Job job = new Job();
        job.setId(params.get(JOB_ID_ATTRIBUTE_NAME)[0]);
        job.setJobStatus(JobStatus.valueOf(params.get(JOB_STATUS_ATTRIBUTE_NAME)[0]));
        job.setListingUrl(params.get(LISTING_URL_ATTRIBUTE_NAME)[0]);
        job.setNotes(params.get(NOTES_ATTRIBUTE_NAME)[0]);
        gateway.save(job);
        response.sendRedirect(request.getContextPath() + JOB_PATH);
    }
}
