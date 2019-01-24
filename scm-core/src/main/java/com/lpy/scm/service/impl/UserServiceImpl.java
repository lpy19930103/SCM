package com.lpy.scm.service.impl;

import com.lpy.scm.DO.UserDO;
import com.lpy.scm.dao.UserMapper;
import com.lpy.scm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDO test() {
        return userMapper.queryUserByid();
    }
}
