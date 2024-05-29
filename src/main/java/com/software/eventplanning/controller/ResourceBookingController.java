package com.software.eventplanning.controller;

import com.software.eventplanning.common.Result;
import com.software.eventplanning.controller.dto.BookingsDTO;
import com.software.eventplanning.service.IResourceBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceBookingController {

    @Autowired
    private IResourceBookingService rb;


    @PostMapping("/resourcebook")
    @ResponseBody
    public Result resourcebook(BookingsDTO bookingsDTO)
    {
        //和已有分配表冲突，拒绝申请
        if(rb.collisiondetectionwithallocations(bookingsDTO)){
            return Result.error(400,"存在分配冲突，拒绝该资源申请");
        }
        //和已有申请表冲突，利用分配算法解决
        if(rb.collisiondetectionwithbookings(bookingsDTO)){
            //算法实现或者函数调用


            return Result.success();
        }
         //无冲突，直接预约
        return Result.success(rb.book(bookingsDTO));
    }
}
