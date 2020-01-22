package com.kandi.controller;

import com.kandi.pojo.Users;
import com.kandi.pojo.bo.UserBO;
import com.kandi.service.UserService;
import com.kandi.utils.CookieUtils;
import com.kandi.utils.JSONResult;
import com.kandi.utils.JsonUtils;
import com.kandi.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/passport")
//@ApiIgnore  代表swagger2会忽略这个controller
@Api(value = "注册登录",tags = {"注册登录的相关接口"})
public class PassportController {

    @Autowired
    private UserService userService;

    @GetMapping("/usernameIsExist")
    @ApiOperation(value = "用户名是否存在",notes = "判断用户名是否存在",httpMethod = "GET")
    public HttpStatus usernameIsExist(@RequestParam String username){
        //判断用户名为null或者是为空字符串“”
        if(StringUtils.isBlank(username)){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        boolean isExist = userService.queryUserNameIsExist(username);
        if(isExist){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        //用户名不存在返回成功
        return HttpStatus.OK;
    }

    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
    @PostMapping("/register")
    public JSONResult register(@Valid @RequestBody UserBO userBO,
                               HttpServletRequest request,
                               HttpServletResponse response){
        boolean isExist = userService.queryUserNameIsExist(userBO.getUsername());
        if(isExist){
            return JSONResult.errorMsg("用户名已经存在");
        }
        Users userResult = userService.createUser(userBO);
        CookieUtils.setCookie(request, response, "user",
                JsonUtils.objectToJson(userResult), true);
        return JSONResult.ok();
    }


    @ApiOperation(value = "用户登录", notes = "用户登录", httpMethod = "POST")
    @PostMapping("/login")
    public JSONResult login(@Valid @RequestBody UserBO userBO,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        String username = userBO.getUsername();
        String password = userBO.getPassword();


        // 1. 实现登录
        Users userResult = userService.queryUserForLogin(username,
                MD5Utils.getMD5Str(password));

        if (userResult == null) {
            return JSONResult.errorMsg("用户名或密码不正确");
        }

        //userResult = setNullProperty(userResult);
        CookieUtils.setCookie(request,response,"user", JsonUtils.objectToJson(userResult),true);
        return JSONResult.ok(userResult);
    }
}
