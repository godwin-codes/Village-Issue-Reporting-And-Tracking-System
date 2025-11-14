package com.village.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import com.village.dao.UserDAO;
import com.village.model.User;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDAO userDAO = new UserDAO();
        User user = userDAO.loginUser(email, password);

        if(user != null){
            HttpSession session = request.getSession();
            session.setAttribute("userId", user.getId());
            session.setAttribute("userName", user.getName());
            session.setAttribute("role", user.getRole());

            if("admin".equals(user.getRole())){
                response.sendRedirect("adminDashboard.jsp");
            } else {
                response.sendRedirect("index.jsp");
            }
        } else {
            response.sendRedirect("login.jsp?error=1");
        }
    }
}
