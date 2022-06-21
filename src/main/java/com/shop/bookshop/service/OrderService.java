package com.shop.bookshop.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.bookshop.entity.Order;

public interface OrderService extends IService<Order> {
    Page<Order> pageOrderListByUserId(Integer current, Integer size, String queryInfo, Integer userId);
    Order disposeOrder(Order order);
    Boolean changeAddressByOrderId(Integer orderId, Integer addressId);
}
