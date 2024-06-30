package com.example.equipment.service;

import com.example.equipment.pojo.Borrow;
import com.example.equipment.pojo.Condtion;
import com.example.equipment.pojo.Equipment;

import java.util.List;

public interface BorrowService {
    String borrow(Integer userId,String deptId,Integer equipmentId);

    String back(int userId, String deptId, Integer equipmentId);

    List<Equipment> listAll(Condtion condtion);

    List<Borrow> listByDept(String deptId);

    String approval(Integer equipmentId);

    List<Equipment> listByMyDept(String deptId);
}
