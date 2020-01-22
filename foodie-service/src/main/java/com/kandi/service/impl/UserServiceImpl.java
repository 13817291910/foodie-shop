package com.kandi.service.impl;

import com.kandi.enumerate.Sex;
import com.kandi.mapper.UsersMapper;
import com.kandi.pojo.Users;
import com.kandi.pojo.bo.UserBO;
import com.kandi.service.UserService;
import com.kandi.utils.MD5Utils;
import org.apache.commons.lang3.time.DateUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
 * 用户相关service实现类
 */
@Service
public class UserServiceImpl implements UserService {

    private static final String USER_FACE = "";

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private Sid sid;


    //如果当前有事务，就使用当前事务，如果当前没有事务，则不开启事务
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUserNameIsExist(String userName) {
        Example userExample = new Example(Users.class);
        Example.Criteria userCriteria = userExample.createCriteria();
        userCriteria.andEqualTo("username",userName);
        Users result = usersMapper.selectOneByExample(userExample);
        return result==null?false:true;
    }

    //如果当前有事务，就使用当前事务，如果当前没有事务，则开启一个新的事务
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users createUser(UserBO userBO) {
        try {
            //使用全局Id生成器来生成user表id
            String userId = sid.nextShort();
            Users user = new Users();
            user.setId(userId);
            user.setUsername(userBO.getUsername());
            //默认昵称
            user.setNickname(userBO.getUsername());
            user.setFace(USER_FACE);
            user.setSex(Sex.secret.type);
            user.setBirthday(DateUtils.parseDate("1900-01-01","yyyy-MM-dd"));
            user.setCreatedTime(new Date());
            user.setUpdatedTime(new Date());
            //对密码进行Md5加密
            user.setPassword(MD5Utils.getMD5Str(userBO.getPassword()));
            //向数据库中插入该条信息
            usersMapper.insert(user);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users queryUserForLogin(String username, String password) {

//        try {
//            Thread.sleep(2500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        Example userExample = new Example(Users.class);
        Example.Criteria userCriteria = userExample.createCriteria();
        //此处的property对应pojo类里的属性名
        userCriteria.andEqualTo("username", username);
        userCriteria.andEqualTo("password", password);
        Users result = usersMapper.selectOneByExample(userExample);
        return result;
    }
}
