package com.software.eventplanning.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.poi.hpsf.Decimal;

import java.sql.Time;
import java.sql.Timestamp;

@Data
@Getter
@Setter
@ToString
@TableName

/* 以下是报销申请的字段 */

public class ExpenseClaim {
    @TableId(value= "claim_id",type = IdType.AUTO)
    private Integer claimId;
    @TableField(value = "activity_id")
    private Integer activityId;
    @TableField(value = "user_id")
    private Integer userId;
    private Integer amount;
    private String description;
    private Status status;
    @TableField(value = "submitted_at")
    private Timestamp submittedTime;
    @TableField(value = "reviewed_at")
    private Timestamp reviewedTime;
    @TableField(value = "reviewed_by")
    private Integer createrId;

}
