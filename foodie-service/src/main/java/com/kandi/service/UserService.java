package com.kandi.service;

import com.kandi.pojo.Users;
import com.kandi.pojo.bo.UserBO;

public interface UserService {

     boolean queryUserNameIsExist (String userName);

     //创建用户
     public Users createUser(UserBO userBO);
}
