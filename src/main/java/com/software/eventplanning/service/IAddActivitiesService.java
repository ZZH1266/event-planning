package com.software.eventplanning.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.software.eventplanning.controller.dto.AddActivitiesDTO;
import com.software.eventplanning.entity.Activities;

public interface IAddActivitiesService extends IService<Activities> {
    Activities AddActivities(AddActivitiesDTO addActivitiesDTO);

    /**
     * 分页展示所有活动
     * @author zzh
     */
    IPage<Activities> showAllActivities(Integer pageNum, Integer pageSize, String activityName, Integer userId);

}
