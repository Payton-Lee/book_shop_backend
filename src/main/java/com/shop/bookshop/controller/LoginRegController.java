package com.shop.bookshop.controller;


import cn.hutool.core.lang.Dict;
import com.shop.bookshop.entity.User;
import com.shop.bookshop.entity.enums.ReturnCode;
import com.shop.bookshop.entity.result.ResultData;
import com.shop.bookshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginRegController {
    private UserService userService;


    @Autowired
    protected void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Object login(@RequestBody User user) {
        Dict data = Dict.create();
        User userInDB = userService.getUserByUsername(user.getUsername());
        if(userInDB == null) {
            return ResultData.fail(ReturnCode.USERNAME_ERROR.code, ReturnCode.USERNAME_ERROR.message);
        } else {
            if(userService.checkPassword(user.getPassword(), userInDB.getPassword())) {
                data.set("username", userInDB.getUsername())
                        .set("msg", "登录成功")
                        .set("token", userService.getToken(userInDB));
                return ResultData.success(ReturnCode.RC200.code, ReturnCode.RC200.message, data);
            }
            return ResultData.fail(ReturnCode.PASSWORD_ERROR.code, ReturnCode.PASSWORD_ERROR.message);
        }
    }
    @PostMapping("/register")
    public Object register(@RequestBody User user) {
        Dict data = Dict.create();
        if(userService.getUserByUsername(user.getUsername()) != null) {
            return ResultData.fail(ReturnCode.USERNAME_EXIST.code, ReturnCode.USERNAME_EXIST.message);
        }
        user.setPassword(userService.getEncryptedPassword(user.getPassword()));
        if(userService.register(user)) {
            data.set("username", user.getUsername()).set("msg", "注册成功");
            return ResultData.success(ReturnCode.RC201.code, ReturnCode.RC201.message, data);
        }
        return ResultData.fail(ReturnCode.RC999.code, ReturnCode.RC999.message);
    }
}
