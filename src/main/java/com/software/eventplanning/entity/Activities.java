package com.software.eventplanning.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@Data
public class Activities {
    @TableId(type = IdType.AUTO)
    private Integer activityId;
    @TableField(value="activity_name")
    private String activityName;
    private String description;
    @TableField(value = "created_by")
    private Integer creatorId;
    private Date startTime;
    private Date endTime;
    @TableField(value = "created_at")
    private Timestamp createdTime;
    @TableField(value = "updated_at")
    private Timestamp updatedTime;  //活动的最后修改时间
    @TableField(value="template_id")
    private  Integer templateId; //模板ID
    private String status;
}
