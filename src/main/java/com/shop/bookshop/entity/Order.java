package com.shop.bookshop.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sp_order")
public class Order {
    private Integer id;
    private Integer userId;
    private String orderNumber;
    private Integer bookId;
    @TableField(exist = false)
    private String bookName;
    @TableField(exist = false)
    private String image;
    private double orderPrice;
    // 支付方式： 0支付宝，1微信，2银行卡
    private Integer orderPay;
    // 支付情况：0未付款，1已付款
    private Integer payStatus;
    // 发货状态：0未发货，1已发货
    private Integer isSend;
    // 收货id
    private Integer addressId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
