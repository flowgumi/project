package com.example.equipment.service;

import com.example.equipment.pojo.Condtion;
import com.example.equipment.pojo.Equipment;

import java.util.List;

public interface EquipmentService {
    void insert(Equipment equipment);

    Equipment getById(Integer id);

    void scrap(Integer id);

    List<Equipment> listAll();
    List<Equipment> listById(Integer id);

    List<Equipment> showScrap();

    List<Equipment> search(Condtion condtion);
}
