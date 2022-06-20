package com.shop.bookshop;

import com.shop.bookshop.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookShopApplicationTests {
    private BookService bookService;
     @Autowired
     public void setBookService(BookService bookService) {
         this.bookService = bookService;
     }

    @Test
    void contextLoads() {
    }
    @Test
    void pageBookList() {
        System.out.println(bookService.pageBookList(1, 4, "北纬"));
    }

}
