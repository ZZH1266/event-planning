package com.software.eventplanning.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ReportsDTO {
   private Integer activityId;
    private Integer userId;
    private Integer  participantCount;
    private  String  overallEffectiveness;
    private String  reportContent;
}
