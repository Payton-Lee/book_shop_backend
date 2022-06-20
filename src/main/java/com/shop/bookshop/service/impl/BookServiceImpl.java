package com.shop.bookshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.bookshop.entity.Book;
import com.shop.bookshop.mapper.BookMapper;
import com.shop.bookshop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {
    private BookMapper bookMapper;

    @Autowired
    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }
    @Override
    public Page<Book> pageBookList(Integer current, Integer size, String queryInfo) {
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        Page<Book> bookPage = new Page<>(current, size);
        if(!StringUtils.isEmpty(queryInfo)) {
            wrapper.like("book_name", queryInfo);
        }
        return bookMapper.pageBookList(bookPage, wrapper);
    }
}
