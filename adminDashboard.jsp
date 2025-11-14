<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.List, com.village.dao.IssueDAO, com.village.model.Issue" %>
<%
    if(session.getAttribute("userId") == null || !"admin".equals(session.getAttribute("role"))){
        response.sendRedirect("login.jsp");
        return;
    }
%>

<jsp:include page="includes/header.jsp" />

<div class="container mt-5">
    <h3>All Reported Issues</h3>
    <%
        IssueDAO issueDAO = new IssueDAO();
        List<Issue> issues = issueDAO.getAllIssues();
    %>
    <table class="table table-bordered mt-3">
        <tr>
            <th>ID</th><th>Title</th><th>Description</th><th>Status</th><th>Category</th><th>User ID</th><th>Action</th>
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
            <td><%= issue.getReportedBy() %></td>
            <td>
                <form action="UpdateStatusServlet" method="post">
                    <input type="hidden" name="issueId" value="<%= issue.getId() %>">
                    <select name="status">
                        <option value="Pending" <%= "Pending".equals(issue.getStatus())?"selected":"" %>>Pending</option>
                        <option value="In Progress" <%= "In Progress".equals(issue.getStatus())?"selected":"" %>>In Progress</option>
                        <option value="Resolved" <%= "Resolved".equals(issue.getStatus())?"selected":"" %>>Resolved</option>
                    </select>
                    <button type="submit" class="btn btn-sm btn-success">Update</button>
                </form>
            </td>
        </tr>
        <% } %>
    </table>
</div>

<jsp:include page="/includes/footer.jsp" />
