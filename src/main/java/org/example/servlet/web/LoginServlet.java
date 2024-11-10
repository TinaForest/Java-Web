package org.example.servlet.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servlet.pojo.User;
import org.example.servlet.service.UserService;
import org.example.servlet.service.impl.UserServiceImpl;

import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private final UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User loginUser = userService.login(new User(null, username, password, null));
        if (loginUser != null) {
            //success
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        } else {
            //failure
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        }
    }
}
