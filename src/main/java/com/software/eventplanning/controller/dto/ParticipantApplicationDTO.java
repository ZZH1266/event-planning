package com.software.eventplanning.controller.dto;

import lombok.Data;

@Data
public class ParticipantApplicationDTO {
    private Integer activityId;
    private Integer userId;
    private String username;
}
