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
import java.util.List;

public class BookServlet extends BaseServlet{
    private final BookService bookService = new BookServiceImpl();

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String name = req.getParameter("name");
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        bookService.addBook(book);
        //req.getRequestDispatcher("/manager/bookServlet?action=list").forward(req, resp);//表单重复提交
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1) + 1;
        resp.sendRedirect(req.getContextPath() + "/mgr/bookServlet?action=page&pageNo=" + pageNo);
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String id = req.getParameter("id");
        bookService.deleteBookById(WebUtils.parseInt(id, 0));
        //resp.sendRedirect(req.getContextPath() + "/mgr/bookServlet?action=list");
        resp.sendRedirect(req.getContextPath() + "/mgr/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        bookService.updateBook(book);
        //resp.sendRedirect(req.getContextPath() + "/mgr/bookServlet?action=list");
        resp.sendRedirect(req.getContextPath() + "/mgr/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = bookService.queryById(WebUtils.parseInt(req.getParameter("id"), 0));
        req.setAttribute("book", book);
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookService.queryBooks();
        req.setAttribute("books", books);
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("mgr/bookServlet?action=page");
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);

    }

}
