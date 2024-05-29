package com.software.eventplanning.controller;

import com.software.eventplanning.common.Result;
import com.software.eventplanning.controller.dto.NoticesDTO;
import com.software.eventplanning.service.INoticesService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/notices")
public class NoticesController {

    @Resource
    private INoticesService noticesService;

    @PostMapping("/send")
    @ResponseBody
    public Result sendNotices(@RequestBody NoticesDTO noticesDTO) {

        return Result.success(noticesService.sendnotice(noticesDTO));
    }
}
