package com.software.eventplanning.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.software.eventplanning.common.Result;
import com.software.eventplanning.entity.Activities;
import com.software.eventplanning.service.IAddActivitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IAddActivitiesService activitiesService;

    @GetMapping("/showCreatedActivities")
    public Result showCreatedActivities(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize,
                                        @RequestParam Integer userId) {
        IPage<Activities> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Activities> wrapper = new QueryWrapper<>();
        wrapper.eq("created_by", userId);
        return Result.success(activitiesService.page(page, wrapper));
    }
}
