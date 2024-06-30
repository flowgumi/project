package com.example.equipment.service.impl;

import com.example.equipment.mapper.EquipmentMapper;
import com.example.equipment.mapper.UserMapper;
import com.example.equipment.pojo.Condtion;
import com.example.equipment.pojo.Equipment;
import com.example.equipment.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    EquipmentMapper equipmentMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public void insert(Equipment equipment) {
        equipment.setCreateTime(LocalDateTime.now());
        equipmentMapper.insert(equipment);
    }

    @Override
    public Equipment getById(Integer id) {
        Equipment equipment = equipmentMapper.getById(id);
        return equipment;
    }

    @Override
    public void scrap(Integer id) {
        equipmentMapper.update(id);
    }

    @Override
    public List<Equipment> listAll() {
        return equipmentMapper.listAll();
    }

    @Override
    public List<Equipment> listById(Integer id) {
        String deptId = userMapper.queryDeptId(id);
        return equipmentMapper.listByDeptId(deptId);
    }

    @Override
    public List<Equipment> showScrap() {
        return equipmentMapper.listAllScrap();
    }
    @Override
    public List<Equipment> search(Condtion condtion) {
        return equipmentMapper.search(condtion);
    }
}
