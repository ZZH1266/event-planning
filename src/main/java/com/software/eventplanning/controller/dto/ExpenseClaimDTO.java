package com.software.eventplanning.controller.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.software.eventplanning.entity.Status;
import lombok.Data;

        import lombok.Data;
import org.apache.poi.hpsf.Decimal;

import java.sql.Timestamp;

/* 用户在网页中报销申请所需的数据 */
@Data
public class ExpenseClaimDTO {
    private Integer activityId;
    private Integer userId;
    private Integer amount;
    private String description;
}
