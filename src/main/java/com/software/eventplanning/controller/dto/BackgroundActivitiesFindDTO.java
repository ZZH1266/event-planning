package com.software.eventplanning.controller.dto;

import lombok.Data;

//用于检索活动（支持部分活动名检索，支持活动ID检索）
@Data
public class BackgroundActivitiesFindDTO {
    Integer myId;//查询者的用户ID
    Integer activityId; //活动ID
    String activityName;//活动名称，支持模糊搜索
    Integer current; //页号
    Integer size;//每页记录数量
}
