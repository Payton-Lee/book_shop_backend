package com.shop.bookshop.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.lang.Dict;
import cn.hutool.crypto.digest.BCrypt;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.bookshop.entity.User;
import com.shop.bookshop.mapper.UserMapper;
import com.shop.bookshop.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public User getUserByUsername(String username) {
        return getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, username), false);
    }

    @Override
    public String getEncryptedPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public Boolean checkPassword(String password, String pw_hash) {
        return BCrypt.checkpw(password, pw_hash);
    }

    @Override
    public String getToken(User user) {
        DateTime now = DateTime.now();
        DateTime newTime = now.offsetNew(DateField.MINUTE, 10);
        Dict payload = Dict.create();
        payload.set(JWTPayload.ISSUED_AT, now)
                .set(JWTPayload.EXPIRES_AT, newTime)
                .set(JWTPayload.NOT_BEFORE, now)
                .set("userId", user.getId())
                .set("username", user.getUsername())
                .set("password", user.getPassword());
        return JWTUtil.createToken(payload, "zhaojuan".getBytes());
    }

    @Override
    public Boolean register(User user) {
        return save(user);
    }
}
