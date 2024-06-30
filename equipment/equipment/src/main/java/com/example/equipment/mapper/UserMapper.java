package com.example.equipment.mapper;

import com.example.equipment.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    void insert(User user);

    User queryUserById(int userId);

    String queryDeptId(int userId);
}
