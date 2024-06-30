package com.example.equipment.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipment {
    private Integer equipmentId;
    private String equipmentName;
    private String equipmentImage;
    private String extra;
    private String deptId;
    private LocalDateTime createTime;
    private int status;
    private String category;
}
