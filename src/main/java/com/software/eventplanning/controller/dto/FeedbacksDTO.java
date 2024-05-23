package com.software.eventplanning.controller.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class FeedbacksDTO {
    private Integer userId;
    private Integer activityId;
    private  Integer rating;
    private  String context;
}
