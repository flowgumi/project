package com.example.equipment.mapper;

import com.example.equipment.pojo.Borrow;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BorrowMapper {
    void borrow(Borrow borrow);

    void delete(Integer equipmentId);

    List<Borrow> searchByDept(String deptId);

    void approval(Integer equipmentId);
    List<Integer> searchByBorrow(String deptId);
}
