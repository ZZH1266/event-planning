package com.software.eventplanning.service.impl;


import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.software.eventplanning.controller.dto.AddActivitiesDTO;
import com.software.eventplanning.entity.Activities;
import com.software.eventplanning.entity.Participants;
import com.software.eventplanning.mapper.ScheduleMapper;
import com.software.eventplanning.service.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl extends ServiceImpl<ScheduleMapper, Participants> implements IScheduleService {

    @Autowired
    ScheduleMapper scheduleMapper;

    @Override
    public List <Activities> ListAllActivities(int userId)
    {
        return scheduleMapper.getActivitiesFromParticipantsByUserId(userId);
    }
    @Override
    public Boolean IsConflictedWhenCreate(int userId, AddActivitiesDTO activitiesDTO)
    {
        List<Activities> all;
        all= scheduleMapper.findCollisionInActivity(userId,new DateTime(activitiesDTO.getStartTime()),new DateTime(activitiesDTO.getEndTime()));
        if(all==null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    @Override
    public Boolean IsConflictedWhenJoin(int userId,int activityId)
    {
      List<Activities> all;
      all=scheduleMapper.findCollisionInParticipants(userId,activityId);
        if(all==null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
