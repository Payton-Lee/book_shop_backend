package com.shop.bookshop.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sp_address")
public class Address {
    private Integer id;
    private Integer userId;
    private String province;
    private String city;
    private String area;
    private String address;
    private String name;
    private String telephone;
}
