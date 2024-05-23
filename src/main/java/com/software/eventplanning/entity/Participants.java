package com.software.eventplanning.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
public class Participants {
    @TableId(type = IdType.AUTO)
    private Integer participantId;
    private Integer activityId;
    private Integer userId;
    @TableField(value = "role")
    private String activityRole;
    private String username;
}
