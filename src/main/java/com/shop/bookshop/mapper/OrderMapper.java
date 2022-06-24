package com.shop.bookshop.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.shop.bookshop.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    @Select("SELECT o.id AS id, o.user_id AS userId, order_number, pay_number, " +
            "o.book_id AS bookId, b.book_name, i.image, o.order_price, " +
            "o.order_pay, o.pay_status, o.is_send, o.count, o.address_id, o.create_time, " +
            "o.update_time FROM sp_order AS o INNER JOIN (sp_book AS b LEFT JOIN " +
            "sp_book_image AS i ON b.id = i.book_id) ON o.book_id = b.id AND o.user_id = #{userId}  ${ew.customSqlSegment}")
    <P extends IPage<Order>> P pageOrderList(P page, @Param(Constants.WRAPPER) Wrapper<Order> queryWrapper, Integer userId);
}
