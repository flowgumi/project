package com.example.equipment.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Borrow {
    private Integer userId;
    private String deptId;
    private Integer equipmentId;
    private String equipmentDeptId;
    private LocalDateTime borrowTime;
    private Integer status;
    private String equipmentName;
}
