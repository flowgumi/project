package com.example.equipment.controller;

import com.example.equipment.pojo.Result;
import com.example.equipment.pojo.User;
import com.example.equipment.service.UserService;
import com.example.equipment.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public Result register(@RequestBody User user){
        log.info("用户注册:{}",user.toString());
        userService.insert(user);
        return Result.success();
    }


    @PostMapping("/login")
    public Result login(@RequestBody User user){
        log.info("用户登录:{}",user.toString());
        User u = userService.check(user);

        if (u != null){
            Map<String, Object> Claims = new HashMap<>();
            Claims.put("userId",u.getUserId());
            Claims.put("deptId",u.getDeptId());
            String jwt = JwtUtils.generateJwt(Claims);
            return Result.success(jwt);
        }

        return Result.error("用户名或密码错误");
    }
}
