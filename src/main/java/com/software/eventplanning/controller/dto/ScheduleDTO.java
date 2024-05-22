package com.software.eventplanning.controller.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ScheduleDTO {
    private Integer eventID;
    private String eventName;
    private String eventAddress;
    private String eventDetail;
    private Timestamp beginTime;
    private Timestamp endTime;
    private Integer eventModel;
    private Integer eventStatus;
}
