package com.software.eventplanning.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.security.Timestamp;

@Data
@Getter
@Setter
@TableName("notification_logs")
public class Notices {
    @TableId(value = "log_id", type = IdType.AUTO)
    Integer logId;
    Integer templateId;
    Integer activityId;
    @TableField(value="sent_by")
    Integer userId;
    @TableField(value="sent_to")
    Integer user2Id;
    @TableField(value="sent_at")
    Timestamp sentTime;
    @JsonIgnore
    String status;
}
