package com.software.eventplanning.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.software.eventplanning.controller.dto.BackgroundActivitiesApproveBookingsDTO;
import com.software.eventplanning.controller.dto.BackgroundActivitiesDeleteDTO;
import com.software.eventplanning.controller.dto.BackgroundActivitiesFindDTO;
import com.software.eventplanning.entity.Activities;
import com.software.eventplanning.entity.Allocations;
import com.software.eventplanning.entity.Bookings;
import com.software.eventplanning.exception.ServiceException;
import com.software.eventplanning.mapper.BackgroundActivitiesMapper;
import com.software.eventplanning.mapper.BackgroundUserMapper;
import com.software.eventplanning.service.IBackgroundActivitiesService;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static com.software.eventplanning.common.Constants.*;

@Service
public class BackgroundActivitiesServiceImpl extends ServiceImpl<BackgroundActivitiesMapper, Activities> implements IBackgroundActivitiesService {
    @Autowired
    BackgroundActivitiesMapper backgroundActivitiesMapper;
    public IPage<Activities> findActivities(BackgroundActivitiesFindDTO backgroundActivitiesFindDTO)
    {
        Page<Activities> page=new Page<>(backgroundActivitiesFindDTO.getCurrent(),backgroundActivitiesFindDTO.getSize());
        QueryWrapper<Activities> queryWrapper=new QueryWrapper<>();
        if(backgroundActivitiesFindDTO.getActivityId()!=0)
        {
            queryWrapper
                    .eq("activity_id",backgroundActivitiesFindDTO.getActivityId());
        }
        if( !StringUtils.isBlank(backgroundActivitiesFindDTO.getActivityName()))
        {
            queryWrapper
                    .like("activity_name",backgroundActivitiesFindDTO.getActivityName());
        }
        IPage<Activities> iPage=backgroundActivitiesMapper.selectPage(page,queryWrapper);
        return iPage;
    }

    public Activities deleteActivity(BackgroundActivitiesDeleteDTO backgroundActivitiesDeleteDTO)
    {
        QueryWrapper<Activities> queryWrapper=new QueryWrapper<>();
        queryWrapper
                .eq("activity_id",backgroundActivitiesDeleteDTO.getActivityId());
        Activities activity=backgroundActivitiesMapper.selectOne(queryWrapper);
        if(activity==null)
        {
            throw new ServiceException(CODE_537,"您要删除的活动不存在");
        }

        int count=0;
        count+=backgroundActivitiesMapper.deleteFromActivitiesById(backgroundActivitiesDeleteDTO.getActivityId());
        count+=backgroundActivitiesMapper.deleteFromActivitiesReportsById(backgroundActivitiesDeleteDTO.getActivityId());
        count+=backgroundActivitiesMapper.deleteFromActivitiesTemplatesById(backgroundActivitiesDeleteDTO.getActivityId());
        count+=backgroundActivitiesMapper.deleteFromBudgetsById(backgroundActivitiesDeleteDTO.getActivityId());
        count+=backgroundActivitiesMapper.deleteFromCommentsById(backgroundActivitiesDeleteDTO.getActivityId());
        count+=backgroundActivitiesMapper.deleteFromExpenseClaimsById(backgroundActivitiesDeleteDTO.getActivityId());
        count+=backgroundActivitiesMapper.deleteFromFeedbackAnalysisById(backgroundActivitiesDeleteDTO.getActivityId());
        count+=backgroundActivitiesMapper.deleteFromFeedbacksById(backgroundActivitiesDeleteDTO.getActivityId());

        List<Integer> logList=backgroundActivitiesMapper.selectLogIdByActivityId(backgroundActivitiesDeleteDTO.getActivityId());//获取所有log_id
        for(int i=0;i<logList.size();i++)
        {
            //根据logId删除notifications_receptions表中的内容
            int logId=logList.get(i);
            count+=backgroundActivitiesMapper.deleteFromNotificationReceptionsById(logId);
        }
        count+=backgroundActivitiesMapper.deleteFromNotificationLogsById(backgroundActivitiesDeleteDTO.getActivityId());
        count+=backgroundActivitiesMapper.deleteFromParticipantApplicationsById(backgroundActivitiesDeleteDTO.getActivityId());
        count+=backgroundActivitiesMapper.deleteFromParticipantsById(backgroundActivitiesDeleteDTO.getActivityId());
        count+=backgroundActivitiesMapper.deleteFromResourceAllocationsById(backgroundActivitiesDeleteDTO.getActivityId());
        count+=backgroundActivitiesMapper.deleteFromResourceBookingsById(backgroundActivitiesDeleteDTO.getActivityId());
        return activity;
    }

    public Allocations approveBookings(BackgroundActivitiesApproveBookingsDTO backgroundActivitiesApproveBookingsDTO)
    {
        int bookingId=backgroundActivitiesApproveBookingsDTO.getBookingId();
        List<Bookings>bookings=backgroundActivitiesMapper.findFromResourceBookingsByBookingId(bookingId);
        if(bookings.size()==0)
        {
            throw new ServiceException(CODE_538,"您要审批的申请ID不存在");
        }
        Bookings booking=bookings.get(0);
        if(booking.getStatus().equals("已审批")||booking.getStatus().equals("拒绝"))
        {
            throw new ServiceException(CODE_539,"该申请已经被处理");
        }

        if(backgroundActivitiesApproveBookingsDTO.isResult()==true)
        {
            //审批通过
            Allocations allocations=new Allocations();
            allocations.setActivityId(booking.getActivityId());
            allocations.setResourceId(booking.getResourceId());
            allocations.setResourceName(booking.getResourceName());
            allocations.setStartTime(booking.getStartTime());
            allocations.setEndTime(booking.getEndTime());
            allocations.setAllocatedAt(Timestamp.valueOf(DateTime.now().toLocalDateTime()));
            //要插入分配表，修改申请表中的状态,调用通知函数
            backgroundActivitiesMapper.insertIntoResourceAllocation(allocations);
            backgroundActivitiesMapper.updateResourceBookingsStatusTrue(booking);
            return allocations;
        }
        else
        {
            //拒绝申请，修改申请表中的状态，调用通知函数
            backgroundActivitiesMapper.updateResourceBookingsStatusFalse(booking);
            return null;
        }
    }
}
