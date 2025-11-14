<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Village Issue System</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/CSS/style.css">

    <style>
        /* Optional fade-in animation */
        .fade-in { opacity: 0; animation: fadeIn 0.8s forwards; }
        @keyframes fadeIn { to { opacity: 1; } }

        /* Push page content down to avoid overlap with fixed navbar */
        body { padding-top: 70px; }
    </style>
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top shadow-sm fade-in">
    <div class="container">
        <a class="navbar-brand" href="index.jsp">Village Issue System</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <% if(session.getAttribute("userName") != null) { %>
                    <li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>
                    <li class="nav-item"><a class="nav-link" href="reportIssue.jsp">Report Issue</a></li>
                    <li class="nav-item"><a class="nav-link" href="viewIssues.jsp">My Issues</a></li>
                    <% if("admin".equals(session.getAttribute("role"))) { %>
                        <li class="nav-item"><a class="nav-link" href="adminDashboard.jsp">Admin Dashboard</a></li>
                    <% } %>
                    <li class="nav-item">
                        <a class="nav-link btn btn-danger text-white ms-2 px-3 py-1" href="LogoutServlet">Logout</a>
                    </li>
                <% } else { %>
                    <li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
                    <li class="nav-item"><a class="nav-link" href="register.jsp">Register</a></li>
                <% } %>
            </ul>
        </div>
    </div>
</nav>
