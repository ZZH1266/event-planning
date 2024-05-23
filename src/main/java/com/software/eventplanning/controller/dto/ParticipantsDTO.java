package com.software.eventplanning.controller.dto;

import lombok.Data;

@Data
public class ParticipantsDTO {
    private Integer activityId;
    private Integer userId;
    private String activityRole;
    private String username;
}
