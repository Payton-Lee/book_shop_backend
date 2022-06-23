package com.shop.bookshop.controller;

import com.shop.bookshop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {
    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/booklist")
    public Object getBookList(@RequestParam Integer current, @RequestParam Integer size, @RequestParam String queryInfo) {
        return bookService.pageBookList(current, size, queryInfo);
    }

    @GetMapping("/{bookId}/booklist")
    public Object getBookListById(@PathVariable Integer bookId){
        return bookService.getBookById(bookId);
    }
}
