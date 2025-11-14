<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%
    if(session.getAttribute("userId") == null){
        response.sendRedirect("login.jsp");
        return;
    }
%>

<jsp:include page="includes/header.jsp" />

<div class="container mt-5">
    <h3>Report an Issue</h3>
    <form action="ReportIssueServlet" method="post">
        <div class="mb-3">
            <label>Title</label>
            <input type="text" name="title" class="form-control" required>
        </div>
        <div class="mb-3">
            <label>Description</label>
            <textarea name="description" class="form-control" rows="4" required></textarea>
        </div>
        <div class="mb-3">
            <label>Category</label>
            <select name="category" class="form-control">
                <option value="Road">Road</option>
                <option value="Water">Water</option>
                <option value="Electricity">Electricity</option>
                <option value="Garbage">Garbage</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>

<jsp:include page="/includes/footer.jsp" />
