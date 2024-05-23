package com.software.eventplanning.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.software.eventplanning.controller.dto.AddActivitiesDTO;
import com.software.eventplanning.entity.Activities;

public interface IAddActivitiesService extends IService<Activities> {
    Activities AddActivities(AddActivitiesDTO addActivitiesDTO);
}
