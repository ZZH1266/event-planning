package com.software.eventplanning.controller;


import com.software.eventplanning.common.Result;
import com.software.eventplanning.controller.dto.ActivityStatusDTO;
import com.software.eventplanning.service.IActivitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/organizer")
public class OrganizerController {
    @Autowired
    private IActivitiesService as;

    @PostMapping("/updatestatus")
    @ResponseBody
    public Result r(@RequestBody ActivityStatusDTO activityStatusDTO){
        String status = activityStatusDTO.getStatus();
        if(status.equals("进行中")||status.equals("筹备中")||status.equals("已完成")){
            return Result.success(as.updatestatus(activityStatusDTO));
        }
        else return Result.error(400,"请填写正确的进度格式");
    }
}
