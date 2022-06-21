package com.shop.bookshop.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.bookshop.entity.Address;
import com.shop.bookshop.mapper.AddressMapper;
import com.shop.bookshop.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

    @Override
    public List<Address> getAddressListByUserId(Integer userId) {
        return list(Wrappers.<Address>lambdaQuery().eq(Address::getUserId, userId));
    }

}
