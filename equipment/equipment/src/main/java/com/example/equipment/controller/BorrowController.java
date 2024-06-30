package com.example.equipment.controller;

import com.example.equipment.pojo.Condtion;
import com.example.equipment.pojo.Result;
import com.example.equipment.service.BorrowService;
import com.example.equipment.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/borrow")
public class BorrowController {
    @Autowired
    private BorrowService borrowService;

    @Autowired
    HttpServletRequest request;

    @GetMapping("/{equipmentId}")
    public Result borrowById(@PathVariable Integer equipmentId){
        Claims claims = JwtUtils.parseJWT(request.getHeader("token"));
        int userId = (int) claims.get("userId");
        String deptId = (String) claims.get("deptId");
        System.out.println(userId);
        log.info("{}部门的{}借走{}",deptId,userId,equipmentId);
        String message = borrowService.borrow(userId,deptId,equipmentId);
        if ("成功".equals(message)){
            return Result.success();
        }
        return Result.error(message);
    }

    @GetMapping("/list")
    public Result listAll(){
        Claims claims = JwtUtils.parseJWT(request.getHeader("token"));
        String deptId = (String) claims.get("deptId");
        Condtion condtion = new Condtion();
        condtion.setDeptId(deptId);
        log.info("查询所有非本部门的可借出设备");
        return Result.success(borrowService.listAll(condtion));
    }

    @GetMapping("/{equipmentId}/back")
    public Result back(@PathVariable Integer equipmentId){
        Claims claims = JwtUtils.parseJWT(request.getHeader("token"));
        int userId = (int) claims.get("userId");
        String deptId = (String) claims.get("deptId");
        log.info("{}部门的{}归还{}",deptId,userId,equipmentId);
        String message = borrowService.back(userId,deptId,equipmentId);
        if ("成功".equals(message)){
            return Result.success();
        }
        return Result.error(message);
    }

    @GetMapping("/dept/list")
    public Result ListByDept(){
        Claims claims = JwtUtils.parseJWT(request.getHeader("token"));
        String deptId = (String) claims.get("deptId");
        log.info("查询本部门所有待审批设备");
        return Result.success(borrowService.listByDept(deptId));
    }

    @GetMapping("/dept/{equipmentId}")
    public Result Approval(@PathVariable Integer equipmentId){
        log.info("批准{}的借出",equipmentId);
        return Result.success(borrowService.approval(equipmentId));
    }

    @GetMapping("/dept")
    public Result ListByMyDept(){
        Claims claims = JwtUtils.parseJWT(request.getHeader("token"));
        String deptId = (String) claims.get("deptId");
        log.info("展示所有本部门借用的设备");
        return Result.success(borrowService.listByMyDept(deptId));
    }
}
