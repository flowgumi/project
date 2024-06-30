package com.example.equipment.service.impl;

import com.example.equipment.mapper.BorrowMapper;
import com.example.equipment.mapper.EquipmentMapper;
import com.example.equipment.pojo.Borrow;
import com.example.equipment.pojo.Condtion;
import com.example.equipment.pojo.Equipment;
import com.example.equipment.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BorrowServiceImpl implements BorrowService {
    @Autowired
    EquipmentMapper equipmentMapper;
    @Autowired
    BorrowMapper borrowMapper;


    @Override
    public String borrow(Integer userId,String deptId,Integer equipmentId) {
        Equipment euqipment = equipmentMapper.getById(equipmentId);
        if (euqipment == null){
            return "设备不存在";
        }
        if (euqipment.getStatus()==1){
            return "设备已报废";
        }
        if (euqipment.getStatus()==2){
            return "设备被借出";
        }
        equipmentMapper.borrow(equipmentId);
        Borrow borrow = new Borrow();
        borrow.setUserId(userId);
        borrow.setDeptId(deptId);
        borrow.setEquipmentId(equipmentId);
        borrow.setEquipmentDeptId(euqipment.getDeptId());
        borrow.setBorrowTime(LocalDateTime.now());
        borrow.setStatus(0);
        borrow.setEquipmentName(euqipment.getEquipmentName());
        borrowMapper.borrow(borrow);
        return "成功";
    }

    @Override
    public String back(int userId, String deptId, Integer equipmentId) {
        Equipment equipment = equipmentMapper.getById(equipmentId);
        if (equipment == null){
            return "设备不存在";
        }
        if (equipment.getStatus()==1){
            return "设备已报废";
        }
        if (equipment.getStatus()==0){
            return "设备未被借出";
        }
        equipmentMapper.back(equipmentId);
        borrowMapper.delete(equipmentId);
        return "成功";
    }

    @Override
    public List<Equipment> listAll(Condtion condtion) {
        return equipmentMapper.listAllBorrow(condtion);
    }

    @Override
    public List<Borrow> listByDept(String deptId) {
        return borrowMapper.searchByDept(deptId);
    }

    @Override
    public String approval(Integer equipmentId) {
        borrowMapper.approval(equipmentId);
        equipmentMapper.approval(equipmentId);
        return "成功";
    }

    @Override
    public List<Equipment> listByMyDept(String deptId) {
        List<Integer> equipmentId = borrowMapper.searchByBorrow(deptId);
        List<Equipment> result = new ArrayList<>();
        for (Integer i : equipmentId) {
            result.add(equipmentMapper.searchByEquipmentId(i));
        }
        return result;
    }


}
