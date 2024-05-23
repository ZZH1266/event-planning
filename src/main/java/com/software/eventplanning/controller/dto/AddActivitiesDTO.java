package com.software.eventplanning.controller.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class AddActivitiesDTO {
    private Integer activityId;
    private String activityName;
    private String description;

    private Integer creatorId;
    private Date startTime;
    private Date endTime;

    private Timestamp createdTime;

    private Timestamp updatedTime;
    private  Integer templateId;
}
