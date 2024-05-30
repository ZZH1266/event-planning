package com.software.eventplanning.controller;

import com.software.eventplanning.common.Result;
import com.software.eventplanning.controller.dto.BookingsDTO;
import com.software.eventplanning.entity.Bookings;
import com.software.eventplanning.service.IResourceBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static com.software.eventplanning.common.Constants.*;

@RestController
public class ResourceBookingController {

    @Autowired
    private IResourceBookingService rb;


    @PostMapping("/resourcebook")
    public Result resourcebook(@RequestBody BookingsDTO bookingsDTO)
    {
        //首先判断申请中的信息是否完整
        if(bookingsDTO.getUserId()==null || bookingsDTO.getActivityId()==null)
        {
            return Result.error(CODE_501,"缺少用户ID或活动ID，系统异常");
        }
        if(bookingsDTO.getResourceId()==null || bookingsDTO.getStartTime()==null || bookingsDTO.getEndTime()==null)
        {
            return Result.error(CODE_503,"申请表中有项为空白");
        }
        //判断申请起始时间是否小于终止时间
        if(bookingsDTO.getStartTime().isAfter(bookingsDTO.getEndTime()))
        {
            return Result.error(CODE_505,"申请的开始时间超过了结束时间");
        }
        //判断申请是否与分配表中记录冲突
        if(rb.collisionDetectionWithAllocations(bookingsDTO))
        {
            return Result.error(CODE_504,"申请场地已被分配");
        }
        else
        {
            //分配表没有冲突，将申请加入申请表
            Bookings bookings=rb.addBooking(bookingsDTO);
            return Result.success("成功插入申请记录",bookings);
        }

    }
}
