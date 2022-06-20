package com.shop.bookshop.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sp_book")
public class Book {
    private Integer id;
    private String bookName;
    private Double price;
    @TableField("ISBN")
    private String ISBN;
    private String publisher;
    private Integer inStock;
    @TableField(exist = false)
    private String image;
}
