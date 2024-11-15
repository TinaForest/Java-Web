package org.example.servlet.service.impl;

import org.example.servlet.dao.BookDao;
import org.example.servlet.dao.impl.BookDaoImpl;
import org.example.servlet.pojo.Book;
import org.example.servlet.pojo.Page;
import org.example.servlet.service.BookService;

import java.util.Arrays;
import java.util.List;

public class BookServiceImpl implements BookService {

    private final BookDao bookDao = new BookDaoImpl();
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page<>();
        page.setPageSize(pageSize);

        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min, max);
        page.setPageTotalCount(pageTotalCount);
        // pageTotal
        int pageTotal = pageTotalCount / pageSize;
        pageTotal += (pageTotalCount % pageSize == 0) ? 0 : 1;
        page.setPageTotal(pageTotal);
        page.setPageNo(pageNo);
        List<Book> items = bookDao.queryForPageItemsByPrice((page.getPageNo() - 1) * pageSize, pageSize, min, max);
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<>();
        page.setPageSize(pageSize);

        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);
        // pageTotal
        int pageTotal = pageTotalCount / pageSize;
        pageTotal += (pageTotalCount % pageSize == 0) ? 0 : 1;
        page.setPageTotal(pageTotal);
        page.setPageNo(pageNo);
        List<Book> items = bookDao.queryForPageItems((page.getPageNo() - 1) * pageSize, pageSize);
        page.setItems(items);
        return page;
    }
}
