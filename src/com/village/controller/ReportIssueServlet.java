package com.village.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import com.village.dao.IssueDAO;

@WebServlet("/ReportIssueServlet")
public class ReportIssueServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");

        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String category = request.getParameter("category");

        IssueDAO issueDAO = new IssueDAO();
        boolean success = issueDAO.reportIssue(userId, title, description, category);

        if(success){
            response.sendRedirect("viewIssues.jsp?success=1");
        } else {
            response.sendRedirect("reportIssue.jsp?error=1");
        }
    }
}
