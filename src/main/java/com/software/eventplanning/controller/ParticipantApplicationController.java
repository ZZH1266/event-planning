package com.software.eventplanning.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.software.eventplanning.common.Result;
import com.software.eventplanning.controller.dto.ParticipantApplicationDTO;
import com.software.eventplanning.entity.Activities;
import com.software.eventplanning.entity.ParticipantApplications;
import com.software.eventplanning.service.IAddActivitiesService;
import com.software.eventplanning.service.IParticipantApplicationService;
import com.software.eventplanning.service.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.software.eventplanning.common.Constants.CODE_330;

@RestController
@RequestMapping("/user/participant-application")
public class ParticipantApplicationController {

    @Autowired
    private IAddActivitiesService addActivitiesService;
    @Autowired
    private IScheduleService scheduleService;
    @Autowired
    private IParticipantApplicationService participantApplicationService;

    @GetMapping
    public Result getActivities(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String activityName,
                                @RequestParam Integer userId) {
        return Result.success(addActivitiesService.showAllActivities(pageNum, pageSize, activityName, userId));
    }

    @PostMapping("/apply")
    public Result apply(@RequestBody ParticipantApplicationDTO participantApplicationDTO) {
        Integer activityId = participantApplicationDTO.getActivityId();
        Integer userId = participantApplicationDTO.getUserId();
        String username = participantApplicationDTO.getUsername();
       if(scheduleService.IsConflictedWhenJoin(participantApplicationDTO.getUserId(),participantApplicationDTO.getActivityId()))
       {
           return Result.error(CODE_330,"参加的活动与你有时间冲突！");
       }
        return Result.success(participantApplicationService.apply(activityId, userId, username));
    }

    @GetMapping("/show")
    public Result showApplications(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize,
                                   @RequestParam Integer userId) {
        IPage<ParticipantApplications> page = new Page<>(pageNum, pageSize);
        QueryWrapper<ParticipantApplications> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        return Result.success(participantApplicationService.page(page, wrapper));
    }
}
