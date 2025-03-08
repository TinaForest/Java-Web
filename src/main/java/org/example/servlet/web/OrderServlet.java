package org.example.servlet.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.example.servlet.pojo.Cart;
import org.example.servlet.pojo.User;
import org.example.servlet.service.OrderService;
import org.example.servlet.service.impl.OrderServiceImpl;
import org.example.servlet.utils.JdbcUtils;

public class OrderServlet extends BaseServlet{
    private OrderService orderService = new OrderServiceImpl();
    /**
     * 生成订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }
        Integer userId = user.getId();
        String orderId = orderService.createOrder(cart, userId);

        req.getSession().setAttribute("orderId", orderId);
//        req.getRequestDispatcher("pages/cart/checkout.jsp").forward(req, resp);
        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
    }
}
