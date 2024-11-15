package org.example.servlet.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servlet.pojo.Book;
import org.example.servlet.pojo.Page;
import org.example.servlet.service.BookService;
import org.example.servlet.service.impl.BookServiceImpl;
import org.example.servlet.utils.WebUtils;

import java.io.IOException;

public class ClientBookServlet extends BookServlet {

    private final BookService bookService = new BookServiceImpl();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("client/bookServlet?action=page");
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        String min1 = req.getParameter("min");
        String max1 = req.getParameter("max");

        int min = WebUtils.parseInt(min1, 0);
        int max = WebUtils.parseInt(max1, Integer.MAX_VALUE);
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize, min, max);
        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");

        if (min1 != null && !min1.trim().isEmpty()) {
            sb.append("&min=").append(req.getParameter("min"));
        }
        if ( max1!= null && !max1.trim().isEmpty()) {
            sb.append("&max=").append(req.getParameter("max"));
        }
        page.setUrl(sb.toString());
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }
}
