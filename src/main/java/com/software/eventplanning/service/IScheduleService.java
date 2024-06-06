package com.software.eventplanning.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.software.eventplanning.controller.dto.AddActivitiesDTO;
import com.software.eventplanning.entity.Activities;
import com.software.eventplanning.entity.Participants;

import java.util.List;

public interface IScheduleService extends IService<Participants>
{
    List<Activities> ListAllActivities(int userId);
    Boolean IsConflictedWhenCreate(int userId, AddActivitiesDTO activitiesDTO);
    Boolean IsConflictedWhenJoin(int userId,int activityId);
}
