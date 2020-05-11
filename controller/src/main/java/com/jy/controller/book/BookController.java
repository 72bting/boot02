package com.jy.controller.book;

import com.jy.model.book.Book;
import com.jy.service.book.BookService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("book/toList")
    String toList(){
        return "book/list";
    }

    @RequestMapping("book/list")
    @ResponseBody
    Map<String, Object> selectBookList(Book book) {
        Map<String, Object> map = bookService.selectBookList(book);
        return map;
    }

    @RequestMapping("book/toAddPage")
    String toAddPage(Book book){
        return "book/add";
    }

    @RequestMapping("/book/insertBook")
    @ResponseBody
    String insertBook(Book book){
        bookService.insertbook(book);
        return "{}";
    }

    @RequiresPermissions(value = {"/book/delete"})
    @RequestMapping("book/deleteBook")
    @ResponseBody
    String deleteBook(Book book){
        bookService.deleteBook(book);
        return "{}";
    }

    @RequestMapping("/book/editBook")
    String selectBookByID(Book book,ModelMap mm){
        Book bk = bookService.selectBookByID(book);
        mm.addAttribute("book",bk);
        return "book/edit";
    }

    @RequestMapping("book/update")
    @ResponseBody
    String updatebook(Book book){
        bookService.updatebook(book);
        return "{}";
    }

}
