<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%
    if(session.getAttribute("userId") == null){
        response.sendRedirect("login.jsp");
        return;
    }
%>

<jsp:include page="includes/header.jsp" />

<div class="container mt-4">
    <h2>Welcome, <%= session.getAttribute("userName") %>!</h2>
    <p>
        <a href="reportIssue.jsp" class="btn btn-primary">Report an Issue</a>
        <a href="viewIssues.jsp" class="btn btn-info">View My Issues</a>
    </p>
</div>

<jsp:include page="/includes/footer.jsp" />
