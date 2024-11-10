package org.example.servlet.web;

import org.example.servlet.pojo.User;
import org.example.servlet.service.UserService;
import org.example.servlet.service.impl.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet{
    UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        if(code.equalsIgnoreCase("abcde")) {
            if (userService.existsUsername(username)) {
                req.getRequestDispatcher("/pages/user/register.jsp").forward(req, resp);
            } else {
                userService.registUser(new User(null, username, password, email));
                req.getRequestDispatcher("/pages/user/register_success.jsp").forward(req, resp);
            }
        } else {
            req.getRequestDispatcher("/pages/user/register.jsp").forward(req, resp);
        }
    }

}
