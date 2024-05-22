package com.software.eventplanning.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "event_user_relation")
public class Participants {
    @TableId(type = IdType.AUTO)
    private Integer participantId;
    private Integer activityId;
    private Integer userId;
    @TableField(value = "role")
    private ActivityRole activityRole;
}


