package org.example.servlet.test;

import org.example.servlet.dao.BookDao;
import org.example.servlet.dao.impl.BookDaoImpl;
import org.example.servlet.pojo.Book;
import org.example.servlet.pojo.Page;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;
public class BookDaoTest {

    BookDao bookDao = new BookDaoImpl();

    @Test
    public void addBook() {
        bookDao.addBook(new Book(null, "tyf", "国哥好帅", new BigDecimal("9.9"), 100, 100, null));
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(21);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(21, "tyf", "tyf真是天才", new BigDecimal("9.9"), 100, 100, null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(21));
    }

    @Test
    public void queryBooks() {
        System.out.println(bookDao.queryBooks());
    }

    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount());
    }

    @Test
    public void queryForPageItems() {
        System.out.println(bookDao.queryForPageItems(8, Page.PAGE_SIZE));
    }

    @Test
    public void queryForPageTotalCountByPrice() {
        System.out.println(bookDao.queryForPageTotalCountByPrice(10, 50));
    }

    @Test
    public void queryForPageItemsByPrice() {
        System.out.println(bookDao.queryForPageItemsByPrice(0, Page.PAGE_SIZE, 10, 50));
    }
}