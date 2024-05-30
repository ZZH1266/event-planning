package com.software.eventplanning.controller;

import com.software.eventplanning.common.Result;
import com.software.eventplanning.controller.dto.ActivitiesDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController("ActivitiesController")
@RequestMapping("/api/activities")
public class ActivitiesController {
    /**
     * 活动列表
     *
     * @return
     */
    @GetMapping(value = "/list")

    public Object list(@RequestParam(value = "activityName", required = false) String activityName
            , @RequestParam(value = "startDate", required = false) String startDate
            , @RequestParam(value = "endDate", required = false) String endDate) {


        List<ActivitiesDTO> rtnList = new ArrayList<>();
        return Result.success("查询成功!", rtnList);
    }
}