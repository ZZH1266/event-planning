package com.software.eventplanning.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class Activities {
    @TableId(type = IdType.AUTO)
    private Integer activityId;
    private String activityName;
    private String description;
    @TableField(value = "created_by")
    private Integer creatorId;
    private Date startTime;
    private Date endTime;
    @TableField(value = "created_at")
    private Timestamp createdTime;
    @TableField(value = "updated_at")
    private Timestamp updatedTime;
}
