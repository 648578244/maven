package com.weidai.action.service.impl;

import com.weidai.action.mapper.UserMapper;
import com.weidai.action.model.UserBean;
import com.weidai.action.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by fuck on 17/1/6.
 */
@Service
public class LoginServiceImpl implements ILoginService{

    @Resource
    private UserMapper userMapper;

    @Override
    public UserBean login(String username, String password) {
        return userMapper.login(username,password);
    }
}
