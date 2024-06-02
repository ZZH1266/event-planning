package com.software.eventplanning.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.software.eventplanning.common.Constants;
import com.software.eventplanning.controller.dto.ActivityStatusDTO;
import com.software.eventplanning.entity.Activities;
import com.software.eventplanning.exception.ServiceException;
import com.software.eventplanning.mapper.ActivitiesMapper;
import com.software.eventplanning.service.IActivitiesService;
import org.springframework.stereotype.Service;

@Service
public class ActivitiesServiceImpl extends ServiceImpl<ActivitiesMapper, Activities> implements IActivitiesService {

    @Override
    public Activities updatestatus(ActivityStatusDTO activityStatusDTO){

        Integer activityId = activityStatusDTO.getActivityId();
        String status = activityStatusDTO.getStatus();
        QueryWrapper<Activities> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("activity_id",activityId);
        Activities one=this.getOne(queryWrapper);
        if(one!=null){
            one.setStatus(status);
            return one;
        }else
        {
            throw new ServiceException(Constants.CODE_400,"该活动不存在");
        }

    }

    @Override
    public String getactivitystatusbyid(Integer activityId){
        QueryWrapper<Activities> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("activity_id",activityId);
        Activities one=this.getOne(queryWrapper);
        if(one!=null){
            return one.getStatus();
        }else{
            throw new ServiceException(Constants.CODE_400,"该活动不存在");
        }
    }
}
