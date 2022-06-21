package com.shop.bookshop;

import com.shop.bookshop.service.AddressService;
import com.shop.bookshop.service.BookService;
import com.shop.bookshop.service.OrderService;
import com.shop.bookshop.utils.CodeGenerateUtils;
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

    private OrderService orderService;
    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
    private AddressService addressService;
    @Autowired
    public void setAddressService(AddressService addressService) {
        this.addressService = addressService;
    }
    @Test
    void contextLoads() {
    }
    @Test
    void pageBookList() {
        System.out.println(bookService.pageBookList(1, 4, "北纬"));
    }


    @Test
    void pageOrderListByUserId() {
        System.out.println(orderService.pageOrderListByUserId(1, 4, "", 1));
    }

    @Test
    void testOrderNumber() {
        System.out.println(CodeGenerateUtils.generateOrderSn(1L));
        System.out.println(CodeGenerateUtils.generateOrderSn(2L));
        System.out.println(CodeGenerateUtils.generateOrderSn(3L));
        System.out.println(CodeGenerateUtils.generateOrderSn(4L));
        System.out.println(CodeGenerateUtils.generateOrderSn(5L));
    }

    @Test
    void testAddressList() {
        System.out.println(addressService.getAddressListByUserId(1));
    }
}
