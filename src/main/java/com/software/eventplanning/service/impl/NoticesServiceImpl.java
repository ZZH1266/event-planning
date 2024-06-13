package com.software.eventplanning.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.software.eventplanning.controller.dto.NoticesDTO;
import com.software.eventplanning.controller.dto.NoticesReceptionsDTO;
import com.software.eventplanning.entity.*;
import com.software.eventplanning.exception.ServiceException;
import com.software.eventplanning.mapper.ActivitiesMapper;
import com.software.eventplanning.mapper.NoticesMapper;
import com.software.eventplanning.mapper.ResourcesMapper;
import com.software.eventplanning.mapper.UsersMapper;
import com.software.eventplanning.service.INoticesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.sql.Wrapper;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static com.baomidou.mybatisplus.core.toolkit.SystemClock.now;

@Service
public class NoticesServiceImpl extends ServiceImpl<NoticesMapper, Notices> implements INoticesService {

    @Autowired
    private NoticesMapper noticesMapper;
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private ActivitiesMapper activitiesMapper;

    @Override
    public Notices sendnotice(NoticesDTO noticesDTO){
        Notices one = new Notices();
        Integer sendId=noticesDTO.getUserId();
        Integer sentId=noticesDTO.getUser2Id();
        Integer activityId=noticesDTO.getActivityId();
        Users userone=usersMapper.selectByUserId(sendId);
        Users usertwo=usersMapper.selectByUserId(sentId);
        Activities activity=activitiesMapper.selectById(activityId);
        if(userone==null||usertwo==null){
            throw new ServiceException(400,"用户不存在");
        }
        if(activity==null){
            throw new ServiceException(400,"活动不存在");
        }
        BeanUtil.copyProperties(noticesDTO, one, true);
        save(one);
        return one;
    }

    @Override
    public List<NoticesInfo> getnoticesInfoByUserId(Integer userId){
        List<NoticesInfo> noticesinfo=noticesMapper.getnoticesInfoByUserId(userId);
        return noticesinfo;
    }

    @Override
    public NoticeReceptions receivenotice(NoticesReceptionsDTO noticesReceptionsDTO){
        NoticeReceptions  one = new NoticeReceptions();
        Integer logId=noticesReceptionsDTO.getLogId();
        Notices notice=noticesMapper.selectById(logId);
        if(notice==null){
            throw new ServiceException(400,"该通知不存在");
        }
        BeanUtil.copyProperties(noticesReceptionsDTO, one, true);
        noticesMapper.addReception(one);
         return one;
    }

    @Override
    public NoticesInfo getnoticesInfoByLogId(Integer logId){
        Notices one = noticesMapper.selectById(logId);
        if(one==null){
            throw new ServiceException(400,"该通知不存在");
        }
        NoticesInfo noticesInfo=noticesMapper.getnoticesInfoByLogId(logId);
        return noticesInfo;
    }

    @Override
    public List<Notices> getnoticesByUserId(Integer userId){
        List<Notices> notices=noticesMapper.getnoticesByUserId(userId);
        return notices;
    }

    @Override
    public List<NoticeReceptions> getreceptionsByUserId(Integer userId){
        List<NoticeReceptions> noticesReceptions=noticesMapper.getreceptionsByUserId(userId);
        return noticesReceptions;
    }
}
