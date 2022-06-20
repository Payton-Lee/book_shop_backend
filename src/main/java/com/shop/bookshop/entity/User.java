package com.shop.bookshop.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sp_user")
public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String sex;
    private String telephone;
}
