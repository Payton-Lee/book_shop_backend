package com.shop.bookshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.bookshop.entity.Address;

import java.util.List;

public interface AddressService extends IService<Address> {
   List<Address> getAddressListByUserId(Integer userId);
}
