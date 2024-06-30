package com.example.equipment.mapper;

import com.example.equipment.pojo.Condtion;
import com.example.equipment.pojo.Equipment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EquipmentMapper {
    void insert(Equipment equipment);

    Equipment getById(Integer id);

    void update(Integer id);

    List<Equipment> listAll();

    List<Equipment> listByDeptId(String deptId);

    void borrow(Integer id);

    List<Equipment> listAllScrap();

    List<Equipment> listAllBorrow(Condtion condtion);

    void back(Integer equipmentId);

    List<Equipment> search(Condtion condtion);

    List<Equipment> listBorrowByDept(String deptId);

    void approval(Integer equipmentId);

    List<Equipment> listByMyDeptId(String deptId);

    Equipment searchByEquipmentId(Integer equipmentId);
}
