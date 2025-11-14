package com.village.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import com.village.dao.UserDAO;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDAO userDAO = new UserDAO();
        boolean success = userDAO.registerUser(name, email, password);

        if(success){
            response.sendRedirect("login.jsp?success=1");
        } else {
            response.sendRedirect("register.jsp?error=1");
        }
    }
}
