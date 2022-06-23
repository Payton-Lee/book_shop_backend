package com.shop.bookshop.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.shop.bookshop.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BookMapper extends BaseMapper<Book> {
    @Select("SELECT sp_book.id AS id, book_name, price, author, ISBN, publisher, in_stock, image FROM" +
            " sp_book_image LEFT JOIN sp_book ON sp_book.id = sp_book_image.book_id\n")
    <P extends IPage<Book>> P pageBookList(P page, @Param(Constants.WRAPPER) Wrapper<Book> queryWrapper);
    @Select("SELECT sp_book.id AS id, book_name, price, author, ISBN, publisher, in_stock, image FROM" +
            " sp_book_image LEFT JOIN sp_book ON sp_book.id = sp_book_image.book_id WHERE sp_book.id = #{bookId}\n")
    Book getBookByBookId(Integer bookId);
}
