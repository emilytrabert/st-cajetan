package com.emilytrabert.stcajetan;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HelloWorld")
public class HelloWorldServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Reached /HelloWorld");
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<h1>Hello World</h1>");
    }
}
