package com.example.equipment.controller;

import com.example.equipment.pojo.Condtion;
import com.example.equipment.pojo.Equipment;
import com.example.equipment.pojo.Result;
import com.example.equipment.service.EquipmentService;
import com.example.equipment.utils.AliOSSUtils;
import com.example.equipment.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/equipment")
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    AliOSSUtils aliOSSUtils;


    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException{
        log.info("文件上传,文件名:{}",image.getOriginalFilename());
        String url = aliOSSUtils.upload(image);
        log.info("文件上传完成,文件访问的url:{}",url);
        return Result.success(url);
    }

    @PostMapping
    public Result add(@RequestBody Equipment equipment){
        Claims claims = JwtUtils.parseJWT(request.getHeader("token"));
        String deptId = (String) claims.get("deptId");
        equipment.setDeptId(deptId);
        log.info("新增设备:{}",equipment);
        equipmentService.insert(equipment);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据id查询设备{}",id);
        Equipment equipment = equipmentService.getById(id);
        return Result.success(equipment);
    }

    @GetMapping
    public Result show(){
        return Result.success(equipmentService.listAll());
    }
    @GetMapping("/dept")
    public Result showByUser(){
        String token = request.getHeader("token");
        String deptId = (String) JwtUtils.parseJWT(token).get("deptId");
        Condtion condtion = new Condtion();
        condtion.setDeptId(deptId);
        log.info("查询所有本部门的设备");
        condtion.setStatus(7);
        return Result.success(equipmentService.search(condtion));
    }

    @DeleteMapping("/{id}")
    public Result scrap(@PathVariable Integer id){
        log.info("根据Id报废设备{}",id);
        equipmentService.scrap(id);
        return Result.success();
    }

    @GetMapping("/scrap")
    public Result showScrap(){
        log.info("展示所有报废设备");
        return Result.success(equipmentService.showScrap());
    }

    @PostMapping("/search")
    public Result search(@RequestBody Condtion condtion){
        log.info("根据条件{}查询设备",condtion);
        if (condtion.getStatus() == 0){
            condtion.setStatus(7);
        }else if (condtion.getStatus() == 7){
            condtion.setStatus(0);
        }
        return Result.success(equipmentService.search(condtion));
    }

    @PostMapping("/dept/search")
    public Result searchByDept(@RequestBody Condtion condtion){
        log.info("根据条件{}查询本部门设备",condtion);
        String token = request.getHeader("token");
        String deptId = (String) JwtUtils.parseJWT(token).get("deptId");
        condtion.setDeptId(deptId);
        if (condtion.getStatus() == 0){
            condtion.setStatus(7);
        }else if (condtion.getStatus() == 7){
            condtion.setStatus(0);
        }
        return Result.success(equipmentService.search(condtion));
    }


}
