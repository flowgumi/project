package com.example.equipment.service;

import com.example.equipment.pojo.User;

public interface UserService {
    void insert(User user);

    User check(User user);
}
