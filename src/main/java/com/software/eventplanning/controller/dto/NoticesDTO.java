package com.software.eventplanning.controller.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class NoticesDTO {
    Integer templateId;
    Integer activityId;
    Integer userId;
    Integer user2Id;

}
