package org.example.servlet.service;

import org.example.servlet.pojo.Book;
import org.example.servlet.pojo.Page;
import org.example.servlet.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BookServiceTest {
    private final BookService bookService = new BookServiceImpl();
    @Test
    public void addBook() {
        bookService.addBook(new Book(null, "tyf", "国哥好帅", new BigDecimal("9.9"), 100, 100, null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(22);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(22, "tyf", "tyf好帅", new BigDecimal("9.9"), 100, 100, null));
    }

    @Test
    public void queryById() {
        System.out.println(bookService.queryById(22));
    }

    @Test
    public void queryBooks() {
        for (Book queryBook : bookService.queryBooks()) {
            System.out.println(queryBook);
        }
    }

    @Test
    public void page() {
        System.out.println(bookService.page(1, Page.PAGE_SIZE));
    }

    @Test
    public void pageByPrice() {
        System.out.println(bookService.pageByPrice(1, Page.PAGE_SIZE, 10, 50));
    }

}