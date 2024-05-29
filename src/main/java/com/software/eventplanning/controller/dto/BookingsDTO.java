package com.software.eventplanning.controller.dto;

import lombok.Data;

import java.security.Timestamp;

@Data
public class BookingsDTO {
    Integer userId;
    Integer activityId;
    Integer resourceId;
    Timestamp startTime;
    Timestamp endTime;
}
