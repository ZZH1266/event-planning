package com.software.eventplanning.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "event_user_relation")
public class Participants {
    private Integer eventId;
    private Integer userId;
    private String userRole;
    private String informTime;
    private String eventAdvice;
}
