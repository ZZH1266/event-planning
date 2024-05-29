package com.software.eventplanning.controller.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ActivitiesTemplateDTO {
    Integer activityId; //活动ID
    Integer templateId; //模板ID
    Integer activitySize;
    String placePlanToUse;
    //以下为户外运动
    String sportsKind;
    String safetyOfficerName;
    //以下为室内运动
    String indoorKind;
    String hostName;
    String teacherName;
}
