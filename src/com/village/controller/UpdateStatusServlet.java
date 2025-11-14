package com.village.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import com.village.dao.IssueDAO;

@WebServlet("/UpdateStatusServlet")
public class UpdateStatusServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int issueId = Integer.parseInt(request.getParameter("issueId"));
        String status = request.getParameter("status");

        IssueDAO issueDAO = new IssueDAO();
        boolean success = issueDAO.updateStatus(issueId, status);

        if(success){
            response.sendRedirect("adminDashboard.jsp?success=1");
        } else {
            response.sendRedirect("adminDashboard.jsp?error=1");
        }
    }
}
