package com.software.eventplanning.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ParticipantApplications {
    @TableId(type = IdType.AUTO)
    private Integer applyId;
    private Integer activityId;
    private Integer userId;
    private String username;
    private String state;
    @TableField(value = "created_at")
    private String applyTime;
    @TableField(value = "result_at")
    private Timestamp resultTime;
}
