package com.software.eventplanning.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.software.eventplanning.common.Result;
import com.software.eventplanning.controller.dto.BackgroundActivitiesApproveBookingsDTO;
import com.software.eventplanning.controller.dto.BackgroundActivitiesDeleteDTO;
import com.software.eventplanning.controller.dto.BackgroundActivitiesFindDTO;
import com.software.eventplanning.entity.Activities;
import com.software.eventplanning.entity.Allocations;
import com.software.eventplanning.exception.ServiceException;
import com.software.eventplanning.service.IBackgroundActivitiesService;
import com.software.eventplanning.service.IBackgroundUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import java.sql.SQLException;

import static com.software.eventplanning.common.Constants.CODE_533;
import static com.software.eventplanning.common.Constants.CODE_534;

//用于后台活动管理的控制类
@RestController
public class BackgroundActivitiesController {
    @Resource
    IBackgroundUserService iBackgroundUserService;
    @Resource
    IBackgroundActivitiesService iBackgroundActivitiesService;
    @PostMapping("/background/activities/find") //查询所有符合条件的活动
    public IPage<Activities> findActivities(@RequestBody BackgroundActivitiesFindDTO backgroundActivitiesFindDTO)
    {
        int myId= backgroundActivitiesFindDTO.getMyId();
        int activityId= backgroundActivitiesFindDTO.getActivityId();
        String activityName=backgroundActivitiesFindDTO.getActivityName();
        int current= backgroundActivitiesFindDTO.getCurrent();;
        int size= backgroundActivitiesFindDTO.getSize();
        if(myId<=0)
        {
            throw new ServiceException(CODE_533,"请确认您是否正确登录");
        }
        if(iBackgroundUserService.getUserRole(myId)==0)
        {
            throw new ServiceException(CODE_534,"权限不足");
        }
        IPage<Activities> iPage=iBackgroundActivitiesService.findActivities(backgroundActivitiesFindDTO);
        return iPage;
    }

    //删除某一个活动
    @PostMapping("/background/activities/delete")
    public Result deleteActivity(@RequestBody BackgroundActivitiesDeleteDTO backgroundActivitiesDeleteDTO)
    {
        int myId=backgroundActivitiesDeleteDTO.getMyId();
        if(myId<=0)
        {
            return Result.error(CODE_533,"请确认您是否正确登录");
        }
        if(iBackgroundUserService.getUserRole(myId)==0)
        {
            return Result.error(CODE_534,"权限不足");
        }
        Activities activity=iBackgroundActivitiesService.deleteActivity(backgroundActivitiesDeleteDTO);
        return Result.success("成功删除",activity);
    }

    //后台审批活动申请的资源
    @PostMapping("/background/activities/aprovebookings")
    public Allocations aproveBookings(@RequestBody BackgroundActivitiesApproveBookingsDTO backgroundActivitiesApproveBookingsDTO) throws SQLException {
        int myId=backgroundActivitiesApproveBookingsDTO.getMyId();
        if(myId<=0)
        {
            throw new ServiceException(CODE_533,"请确认您是否正确登录");
        }
        if(iBackgroundUserService.getUserRole(myId)==0)
        {
            throw new ServiceException(CODE_534,"权限不足");
        }
        Allocations allocations=iBackgroundActivitiesService.approveBookings(backgroundActivitiesApproveBookingsDTO);
        return allocations;
    }
}
