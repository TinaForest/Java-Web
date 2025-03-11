package org.example.servlet.web;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.example.servlet.pojo.User;
import org.example.servlet.service.UserService;
import org.example.servlet.service.impl.UserServiceImpl;
import org.example.servlet.utils.WebUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import com.pig4cloud.captcha.utils.CaptchaJakartaUtil;


public class UserServlet extends BaseServlet {
    private final UserService userService = new UserServiceImpl();

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());
        User loginUser = userService.login(user);
        if (loginUser != null) {
            //success
            //保存用户登录信息到session
            req.getSession().setAttribute("user", loginUser);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        } else {
            req.setAttribute("msg", "用户名或密码错误！");
            req.setAttribute("username", username);
            //failure
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        }
    }

    protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        if(CaptchaJakartaUtil.ver(code, req)) {
            if (userService.existsUsername(username)) {
                req.setAttribute("msg", "用户名已存在！");
                req.setAttribute("username", username);
                req.setAttribute("email", email);
                req.getRequestDispatcher("/pages/user/register.jsp").forward(req, resp);
            } else {
                User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());
                userService.registUser(user);
                req.getRequestDispatcher("/pages/user/register_success.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("msg", "验证码错误！");
            req.setAttribute("username", username);
            req.setAttribute("email", email);
            req.getRequestDispatcher("/pages/user/register.jsp").forward(req, resp);
        }

        CaptchaJakartaUtil.clear(req);
    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath() + "/");

    }

    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数
        String username = req.getParameter("username");
        //调用service
        boolean existsUsername = userService.existsUsername(username);
        Map<String, Object> map = new HashMap<>();
        map.put("existsUsername", existsUsername);
        Gson gson = new Gson();
        String json = gson.toJson(map);
        //通过相应的字符输出流输出
        resp.getWriter().write(json);

    }
}
