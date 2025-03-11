package org.example.servlet.web;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servlet.pojo.Book;
import org.example.servlet.pojo.Cart;
import org.example.servlet.pojo.CartItem;
import org.example.servlet.service.BookService;
import org.example.servlet.service.impl.BookServiceImpl;
import org.example.servlet.utils.WebUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet {
    /**
     * 加入购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BookService bookService = new BookServiceImpl();
        //获取请求的参数商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //调用bookService得到图书信息
        Book book = bookService.queryById(id);
        //把图书信息转化成商品项CartItem
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //调用Cart.addItem()添加商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        req.getSession().setAttribute("lastName", cartItem.getName());
        //重定向到商品列表页面
        resp.sendRedirect(req.getHeader("referer"));
    }

    /**
     * 删除商品项
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);

        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if (cart != null) {
            cart.deleteItem(id);
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }


    /**
     * 清空购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.clear();
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int count = WebUtils.parseInt(req.getParameter("count"), 1);

        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if (cart != null) {
            cart.updateCount(id, count);
            resp.sendRedirect(req.getHeader("Referer"));
        }

    }

    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookService bookService = new BookServiceImpl();
        //获取请求的参数商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //调用bookService得到图书信息
        Book book = bookService.queryById(id);
        //把图书信息转化成商品项CartItem
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //调用Cart.addItem()添加商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        req.getSession().setAttribute("lastName", cartItem.getName());
        //返回购物车总的商品数量和最后一个cartItem的名字
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("totalCount", cart.getTotalCount());
        resultMap.put("lastName", cartItem.getName());
        Gson gson = new Gson();
        String gsonJson = gson.toJson(resultMap);
        resp.getWriter().write(gsonJson);
    }
}
