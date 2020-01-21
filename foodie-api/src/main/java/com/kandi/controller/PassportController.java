package com.kandi.controller;

import com.kandi.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/passport")
public class PassportController {

    @Autowired
    private UserService userService;

    @GetMapping("/usernameIsExist")
    public HttpStatus usernameIsExist(@RequestParam String username){
        //判断用户名为null或者是为空字符串“”
        if(StringUtils.isNotBlank(username)){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        boolean isExist = userService.queryUserNameIsExist(username);
        if(isExist){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        //用户名不存在返回成功
        return HttpStatus.OK;
    }
}
