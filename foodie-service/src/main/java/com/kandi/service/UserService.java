package com.kandi.service;

import com.kandi.pojo.Users;
import com.kandi.pojo.bo.UserBO;

public interface UserService {

     boolean queryUserNameIsExist (String userName);

     //创建用户
     Users createUser(UserBO userBO);

     /**
      * 检索用户名和密码是否匹配，用于登录
      */
     Users queryUserForLogin(String username, String password);
}
