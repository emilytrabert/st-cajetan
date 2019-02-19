package com.emilytrabert.stcajetan.servlet;

import static com.emilytrabert.stcajetan.Constants.JOB_DELETE_PATH;
import static com.emilytrabert.stcajetan.Constants.JOB_PATH;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emilytrabert.stcajetan.gateway.DynamoDBGateway;

@WebServlet(JOB_DELETE_PATH)
public class JobsDeleteServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    static final String JOB_ID_ATTRIBUTE_NAME = "jobId";

    private final DynamoDBGateway gateway;

    public JobsDeleteServlet() {
        this.gateway = new DynamoDBGateway();
    }

    JobsDeleteServlet(DynamoDBGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> params = request.getParameterMap();
        gateway.delete(params.get(JOB_ID_ATTRIBUTE_NAME)[0]);
        response.sendRedirect(request.getContextPath() + JOB_PATH);
    }
}
