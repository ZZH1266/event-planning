package com.software.eventplanning.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.security.Timestamp;
import java.sql.Time;

@Data
public class NoticesInfo {
    Integer logId;
    Integer templateId;
    Integer activityId;
    Integer sentBy;
    Integer sentTo;
    @JsonIgnore
    Timestamp sendAt;
    String status;
    String templateName;
    String subject;
    String body;
    @JsonIgnore
    Timestamp createdAt;
    @JsonIgnore
    Timestamp updatedAt;
}
