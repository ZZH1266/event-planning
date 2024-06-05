package com.software.eventplanning.controller.dto;

import lombok.Data;

@Data
public class BackgroundActivitiesDeleteDTO {
    Integer myId;//操作者的ID
    Integer activityId;//待删除的活动ID
}
