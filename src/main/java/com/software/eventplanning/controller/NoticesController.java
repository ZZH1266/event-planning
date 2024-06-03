package com.software.eventplanning.controller;

import com.software.eventplanning.common.Result;
import com.software.eventplanning.controller.dto.NoticesDTO;
import com.software.eventplanning.controller.dto.NoticesReceptionsDTO;
import com.software.eventplanning.entity.Notices;
import com.software.eventplanning.service.INoticesService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    @GetMapping("/getall")
    @ResponseBody
    public Result getAllNoticesByUserId(@RequestParam("userId") Integer userId) {
        List<Notices> notices=noticesService.getnoticesByUserId(userId);
        if(notices!=null&&notices.size()>0){
            return Result.success(notices);
        }
        else return Result.error(400,"当前没有收到任何活动通知");
    }

    @PostMapping("/receive")
    @ResponseBody
    public Result receiveNotices(@RequestBody NoticesReceptionsDTO noticesReceptionsDTO) {

          return Result.success(noticesService.receivenotice(noticesReceptionsDTO));
    }
}
