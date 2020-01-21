package com.kandi.service.impl;

import com.kandi.enumerate.Sex;
import com.kandi.mapper.UsersMapper;
import com.kandi.pojo.Users;
import com.kandi.pojo.bo.UserBO;
import com.kandi.service.UserService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.text.ParseException;
import java.util.Date;

/**
 * 用户相关service实现类
 */
@Service
public class UserServiceImpl implements UserService {

    private static final String USER_FACE = "";

    @Autowired
    private UsersMapper usersMapper;

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

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users createUser(UserBO userBO) {
        try {
            Users user = new Users();
            user.setUsername(userBO.getUsername());
            //默认昵称
            user.setNickname(userBO.getUsername());
            user.setFace(USER_FACE);
            user.setSex(Sex.secret.type);
            user.setBirthday(DateUtils.parseDate("1900-01-01","yyyy-MM-dd"));
            user.setCreatedTime(new Date());
            user.setUpdatedTime(new Date());
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
