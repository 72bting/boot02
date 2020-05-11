package com.jy.model.book;

import com.jy.common.PageUtil;

import java.io.Serializable;

public class Book extends PageUtil{

    private int bookID;

    private String bookName;

    private String bookAuthor;

    private int bookType;

    private float bookPrice;

    private String bookDesc;

    private String bookDateStr;

    private String bookStr;

    public String getBookStr() {
        return bookStr;
    }

    public void setBookStr(String bookStr) {
        this.bookStr = bookStr;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public int getBookType() {
        return bookType;
    }

    public void setBookType(int bookType) {
        this.bookType = bookType;
    }

    public float getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(float bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getBookDesc() {
        return bookDesc;
    }

    public void setBookDesc(String bookDesc) {
        this.bookDesc = bookDesc;
    }

    public String getBookDateStr() {
        return bookDateStr;
    }

    public void setBookDateStr(String bookDateStr) {
        this.bookDateStr = bookDateStr;
    }
}
