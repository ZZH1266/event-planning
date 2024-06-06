package com.software.eventplanning.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.software.eventplanning.common.Constants;
import com.software.eventplanning.common.Result;
import com.software.eventplanning.entity.Activities;
import com.software.eventplanning.entity.Participants;
import com.software.eventplanning.exception.ServiceException;
import com.software.eventplanning.service.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ScheduleController {

    @Autowired
    private IScheduleService scheduleService;

    @GetMapping("/showActivitiesBySchedule")
    private Result ShowAllActivities(@RequestParam Integer userId)
    {
        List<Activities> all=scheduleService.ListAllActivities(userId);
        try{
            return Result.success(all);
        }catch (Exception e){
            throw  new ServiceException(Constants.CODE_328,"查询错误");
        }
    }
}
