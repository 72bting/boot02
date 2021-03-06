package com.jy.service.book.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jy.mapper.book.BookMapper;
import com.jy.model.book.Book;
import com.jy.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public Map<String, Object> selectBookList(Book book) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap();
        //开启分页
        PageHelper.startPage(book.getPage(), book.getLimit());
        List<Book> list = bookMapper.selectBookList(book);
        //转化
        Page page = (Page) list;
        //获取总条数
        long total = page.getTotal();

        map.put("code", 0);
        map.put("msg","");
        map.put("data", list);
        map.put("count", total);
        return map;
    }

    @Override
    public void insertbook(Book book) {
        bookMapper.insertbook(book);
    }

    @Override
    public void deleteBook(Book book) {
        bookMapper.deleteBook(book);
    }

    @Override
    public Book selectBookByID(Book book) {
        return bookMapper.selectBookByID(book);
    }

    @Override
    public void updatebook(Book book) {
        bookMapper.updatebook(book);
    }
}
