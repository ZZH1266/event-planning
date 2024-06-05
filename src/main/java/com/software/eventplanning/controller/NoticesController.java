package com.software.eventplanning.controller;

import com.software.eventplanning.common.Result;
import com.software.eventplanning.controller.dto.NoticesDTO;
import com.software.eventplanning.controller.dto.NoticesReceptionsDTO;
import com.software.eventplanning.entity.Notices;
import com.software.eventplanning.entity.NoticesInfo;
import com.software.eventplanning.service.INoticesService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<NoticesInfo> noticesinfo=noticesService.getnoticesInfoByUserId(userId);
        if(noticesinfo!=null&&noticesinfo.size()>0){
            return Result.success(noticesinfo);
        }
        else return Result.error(400,"当前没有收到任何活动通知");
    }

    @PostMapping("/receive")
    @ResponseBody
    public Result receiveNotices(@RequestBody NoticesReceptionsDTO noticesReceptionsDTO) {

          return Result.success(noticesService.receivenotice(noticesReceptionsDTO));
    }

    @GetMapping("/viewnotice")
    @ResponseBody
    public Result viewNotice(@RequestParam Integer logId) {
        return Result.success(noticesService.getnoticesInfoByLogId(logId));
    }

    @GetMapping("/noticescount")
    @ResponseBody
    public Result noticescount(@RequestParam Integer userId) {
        Integer sendcount=noticesService.getnoticesByUserId(userId).size();
        Integer receivecount=noticesService.getreceptionsByUserId(userId).size();
        Map<String,Integer> map=new HashMap<>();
        map.put("已发送",sendcount);
        map.put("已接收",receivecount);
        return Result.success(map);
    }
}
