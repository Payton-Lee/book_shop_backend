package com.shop.bookshop.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.bookshop.entity.Book;

public interface BookService extends IService<Book> {
    Page<Book> pageBookList(Integer current, Integer size, String queryInfo);
}
