package org.example.servlet.service;

import org.example.servlet.pojo.Book;
import org.example.servlet.pojo.Page;

import java.util.List;

public interface BookService {
    void addBook(Book book);

    void deleteBookById(Integer id);

    void updateBook(Book book);

    Book queryById(Integer id);

    List<Book> queryBooks();

    Page<Book> page(int pageNo, int pageSize);

    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
