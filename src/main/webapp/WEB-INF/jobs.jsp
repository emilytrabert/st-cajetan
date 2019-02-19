<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Jobs</title>
</head>

<body>
    <table style="width: 100%">
        <tr>
            <th>Job Id</th>
            <th>Job Status</th>
            <th>Listing URL</th>
            <th>Notes</th>
            <th>Delete</th>
        </tr>
        <c:forEach items="${jobs}" var="job">
            <tr>
                <td>${job.id}</td>
                <td>${job.jobStatus}</td>
                <td><a href="${job.listingUrl}">Listing</a></td>
                <td>${job.notes}</td>
                <td><form action="/jobs/delete" method="post"><input style="display:none" type="text" name="jobId" value="${job.id}"><input type="submit" value="Delete"></form></td>
            </tr>
        </c:forEach>
    </table>

    <form action="/jobs" method="post">
        Listing URL: <input type="text" name="listingUrl"><br>
        Notes: <input type="text" name="notes"><br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>