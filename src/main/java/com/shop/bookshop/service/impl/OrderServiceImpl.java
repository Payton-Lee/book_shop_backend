package com.shop.bookshop.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.bookshop.entity.Order;
import com.shop.bookshop.mapper.OrderMapper;
import com.shop.bookshop.service.OrderService;
import com.shop.bookshop.utils.CodeGenerateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    private OrderMapper orderMapper;

    @Autowired
    private void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }
    // 根据用户id查询订单信息，并分页显示
    @Override
    public Page<Order> pageOrderListByUserId(Integer current, Integer size, String queryInfo, Integer userId) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        Page<Order> orderPage = new Page<>(current, size);
        if(!StringUtils.isEmpty(queryInfo)) {
            wrapper.like("book_name", queryInfo);
        }
        return orderMapper.pageOrderList(orderPage, wrapper, userId);
    }

    // 处理订单函数
    @Override
    public Order disposeOrder(Order order) {
        order.setCreateTime(LocalDateTime.now());
        order.setIsSend(0);
        order.setOrderNumber(CodeGenerateUtils.generateOrderSn((long) order.getUserId()));
        order.setPayNumber(CodeGenerateUtils.generateUnionPaySn());
        return order;
    }

    @Override
    public Boolean changeAddressByOrderId(Integer orderId, Integer addressId) {
        Order orderInDB = getById(orderId);
        orderInDB.setAddressId(addressId);
        return updateById(orderInDB);
    }
}
