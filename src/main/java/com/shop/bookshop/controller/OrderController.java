package com.shop.bookshop.controller;

import cn.hutool.core.lang.Dict;
import com.shop.bookshop.entity.Address;
import com.shop.bookshop.entity.Order;
import com.shop.bookshop.entity.enums.ReturnCode;
import com.shop.bookshop.entity.result.ResultData;
import com.shop.bookshop.service.AddressService;
import com.shop.bookshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    private OrderService orderService;
    private AddressService addressService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
    @Autowired
    public void setAddressService(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/order/{userId}/orderlist")
    public Object orderList(@RequestParam Integer current, @RequestParam Integer size, @RequestParam String queryInfo, @PathVariable Integer userId) {
        return orderService.pageOrderListByUserId(current, size, queryInfo, userId);
    }

    @PostMapping("/order/neworder")
    public Object newOrder(@RequestBody Order order) {
        Dict data = Dict.create();
        if(orderService.save(orderService.disposeOrder(order))) {
            data.set("msg", "新建订单成功");
            return ResultData.success(ReturnCode.RC201.code, ReturnCode.RC201.message, data);
        } else {
            return ResultData.fail(ReturnCode.RC999.code, ReturnCode.RC999.message);
        }
    }

    @GetMapping("/order/{addressId}/address")
    public Object showAddress(@PathVariable Integer addressId) {
        return addressService.getById(addressId);
    }

    @GetMapping("/order/{userId}/addresslist")
    public Object addressList(@PathVariable Integer userId) {
        return addressService.getAddressListByUserId(userId);
    }

    @PutMapping("/order/{orderId}/changeaddress/{addressId}")
    public Object changeAddress(@PathVariable Integer orderId, @PathVariable Integer addressId) {
        Order orderInDB = orderService.getById(orderId);
        Dict data = Dict.create();
        if(orderInDB.getIsSend() == 1) {
            data.set("msg", "订单已发货，无法修改订单地址");
            return ResultData.fail(ReturnCode.RC999.code, ReturnCode.RC999.message, data);
        }
        if(orderService.changeAddressByOrderId(orderId, addressId)) {
            data.set("msg", "订单地址修改成功");
            return ResultData.success(ReturnCode.RC201.code, ReturnCode.RC201.message, data);
        }
        return ResultData.fail(ReturnCode.RC999.code, ReturnCode.RC999.message);
    }

    @PostMapping("/address/newaddress")
    public Object newAddress(@RequestBody Address address) {
        if(addressService.save(address)) {
            Dict data = Dict.create();
            data.set("name", address.getName())
                    .set("msg", "新增地址成功");
            return ResultData.success(ReturnCode.RC201.code, ReturnCode.RC201.message, data);
        }
        return ResultData.fail(ReturnCode.RC999.code, ReturnCode.RC999.message);
    }
    @PutMapping("/address/editaddress")
    public Object editAddress(@RequestBody Address address) {
        Address addressInDB = addressService.getById(address.getId());
        Dict data = Dict.create();
        if(addressInDB == null) {
            data.set("msg", "地址不存在");
            return ResultData.fail(ReturnCode.RC999.code, ReturnCode.RC999.message, data);
        }
        if(addressService.updateById(address)) {
            data.set("name", address.getName())
                    .set("msg", "修改地址成功");
            return ResultData.success(ReturnCode.RC201.code, ReturnCode.RC201.message, data);
        }
        return ResultData.fail(ReturnCode.RC999.code, ReturnCode.RC999.message);
    }
    @DeleteMapping("/address/{addressId}/deleteaddress")
    public Object deleteAddress(@PathVariable Integer addressId) {
        Address addressInDB = addressService.getById(addressId);
        Dict data = Dict.create();
        if(addressInDB == null) {
            data.set("msg", "地址不存在");
            return ResultData.fail(ReturnCode.RC999.code, ReturnCode.RC999.message, data);
        }
        if(addressService.removeById(addressId)) {
            data.set("name", addressInDB.getName()).set("msg", "删除地址成功");
            return ResultData.success(ReturnCode.RC201.code, ReturnCode.RC201.message, data);
        }
        return ResultData.fail(ReturnCode.RC999.code, ReturnCode.RC999.message);
    }
}
