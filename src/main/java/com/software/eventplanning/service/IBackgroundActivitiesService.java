package com.software.eventplanning.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.software.eventplanning.controller.dto.BackgroundActivitiesApproveBookingsDTO;
import com.software.eventplanning.controller.dto.BackgroundActivitiesDeleteDTO;
import com.software.eventplanning.controller.dto.BackgroundActivitiesFindDTO;
import com.software.eventplanning.entity.Activities;
import com.software.eventplanning.entity.Allocations;

public interface IBackgroundActivitiesService extends IService<Activities> {
    IPage<Activities> findActivities(BackgroundActivitiesFindDTO backgroundActivitiesFindDTO);
    Activities deleteActivity(BackgroundActivitiesDeleteDTO backgroundActivitiesDeleteDTO);
    Allocations approveBookings(BackgroundActivitiesApproveBookingsDTO backgroundActivitiesApproveBookingsDTO);
}
