package com.software.eventplanning.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.software.eventplanning.controller.dto.ActivityStatusDTO;
import com.software.eventplanning.entity.Activities;

public interface IActivitiesService extends IService<Activities> {
    Activities updatestatus(ActivityStatusDTO activityStatusDTO);
    String  getactivitystatusbyid(Integer activityId);
}
