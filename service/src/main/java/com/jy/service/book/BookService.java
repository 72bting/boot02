package com.jy.service.book;

import com.jy.model.book.Book;

import java.util.Map;

public interface BookService {

    Map<String,Object> selectBookList(Book book);

    void insertbook(Book book);

    void deleteBook(Book book);

    Book selectBookByID(Book book);

    void updatebook(Book book);
}
