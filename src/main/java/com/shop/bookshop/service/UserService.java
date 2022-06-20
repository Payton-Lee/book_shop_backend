package com.shop.bookshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.bookshop.entity.User;

public interface UserService extends IService<User> {
    User getUserByUsername(String username);
    String getEncryptedPassword(String password);
    Boolean checkPassword(String password, String pw_hash);
    String getToken(User user);
    Boolean register(User user);
}
