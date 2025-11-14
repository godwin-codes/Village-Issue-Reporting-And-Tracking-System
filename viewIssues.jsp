<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.List, com.village.dao.IssueDAO, com.village.model.Issue" %>
<%
    if(session.getAttribute("userId") == null){
        response.sendRedirect("login.jsp");
        return;
    }
%>

<jsp:include page="includes/header.jsp" />

<div class="container mt-5">
    <h3>My Reported Issues</h3>
    <%
        int userId = (int) session.getAttribute("userId");
        IssueDAO issueDAO = new IssueDAO();
        List<Issue> issues = issueDAO.getUserIssues(userId);
    %>
    <table class="table table-bordered mt-3">
        <tr>
            <th>ID</th><th>Title</th><th>Description</th><th>Status</th><th>Category</th><th>Reported At</th>
        </tr>
        <%
            for(Issue issue : issues){
        %>
        <tr>
            <td><%= issue.getId() %></td>
            <td><%= issue.getTitle() %></td>
            <td><%= issue.getDescription() %></td>
            <td><%= issue.getStatus() %></td>
            <td><%= issue.getCategory() %></td>
            <td><%= issue.getCreatedAt() %></td>
        </tr>
        <% } %>
    </table>
</div>

<jsp:include page="/includes/footer.jsp" />
