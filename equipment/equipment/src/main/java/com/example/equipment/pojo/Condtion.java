package com.example.equipment.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Condtion {
    private int equipmentId;
    private String category;
    private int status;
    private String deptId;
}
