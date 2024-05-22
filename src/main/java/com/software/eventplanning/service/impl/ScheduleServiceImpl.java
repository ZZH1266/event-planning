package com.software.eventplanning.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.software.eventplanning.controller.dto.ScheduleDTO;
import com.software.eventplanning.entity.Activities;
import com.software.eventplanning.mapper.ScheduleMapper;
import com.software.eventplanning.service.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ScheduleServiceImpl extends ServiceImpl<ScheduleMapper, Activities> implements IScheduleService {

    @Autowired
    private ScheduleMapper scheduleMapper;

    public boolean isConflict(ScheduleDTO scheduleDTO) {
        Timestamp startTime = scheduleDTO.getBeginTime();
        Timestamp endTime = scheduleDTO.getEndTime();
        String address = scheduleDTO.getEventAddress();
        return false;
    }
}
