package com.example.equipment.service.impl;

import com.example.equipment.mapper.UserMapper;
import com.example.equipment.pojo.User;
import com.example.equipment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }

    @Override
    public User check(User user) {
        return userMapper.queryUserById(user.getUserId());
    }
}
