package com.software.eventplanning.controller.dto;

import cn.hutool.core.date.DateTime;
import lombok.Data;

import java.security.Timestamp;

@Data
//预约表单传来的内容
public class BookingsDTO {
    Integer userId;
    Integer activityId;
    Integer resourceId;
    DateTime startTime;
    DateTime endTime;
}
