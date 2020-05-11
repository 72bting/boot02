package com.jy.mapper.book;

import com.jy.model.book.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface BookMapper {


    @Select("select b_id bookID, b_name bookName," +
            " b_author bookAuthor, b_type bookType," +
            " b_price bookPrice, b_desc bookDesc," +
            " date_format(b_date, '%Y-%m-%d') bookDateStr " +
            " from t_books")
    List<Book> selectBookList(Book book);

    @Insert("insert into t_books" +
            " (b_name, b_author, b_type," +
            " b_price, b_desc, b_date)" +
            " values" +
            " (#{bookName}, #{bookAuthor}, #{bookType}," +
            " #{bookPrice}, #{bookDesc}, str_to_date(#{bookDateStr},'%Y-%m-%d'))")
    void insertbook(Book book);


    @Delete("<script>" +
            " delete from" +
            " t_books WHERE b_id IN " +
            " <foreach collection='bookStr.split(\",\")' item='item' open=' (' separator=',' close=')'>" +
            " #{item}" +
            " </foreach>" +
            " </script>")
    void deleteBook(Book book);

    @Select("select b_id bookID, b_name bookName," +
            " b_author bookAuthor, b_type bookType," +
            " b_price bookPrice, b_desc bookDesc," +
            " date_format(b_date, '%Y-%m-%d') bookDateStr " +
            " from t_books" +
            " where b_id = #{bookID}")
    Book selectBookByID(Book book);

    @Update("update t_books set" +
            " b_name = #{bookName}," +
            " b_author = #{bookAuthor}," +
            " b_type = #{bookType}," +
            " b_price = #{bookPrice}," +
            " b_desc = #{bookDesc}," +
            " b_date = str_to_date(#{bookDateStr},'%Y-%m-%d')" +
            " where b_id = #{bookID}")
    void updatebook(Book book);
}
