package com.software.eventplanning.controller;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import com.software.eventplanning.common.Result;
import com.software.eventplanning.controller.dto.BookingsDTO;
import com.software.eventplanning.entity.Bookings;
import com.software.eventplanning.service.IResourceBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;

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
        //判断申请表开始时间是否在当前时间之前
        if(bookingsDTO.getStartTime().isBefore(DateTime.now()))
        {
            return Result.error(CODE_507,"申请的开始时间不能早于目前的时间");
        }
        //判断预约时间是否大于1个小时（最少预约1个小时）
        Calendar startCalendar=bookingsDTO.getStartTime().toCalendar();
        startCalendar.add(Calendar.HOUR,1);
        Calendar endCalendar=bookingsDTO.getEndTime().toCalendar();
        if(startCalendar.compareTo(endCalendar)>0) //返回值大于0，代表startCalendar时间在endCalendar后面，也就是开始时间+1h的结果在结束时间后面，预约时间不足1h
        {
            return Result.error(CODE_508,"预约场地的时长必须大于1个小时");
        }
        //检测预约时间段是否跨天
        if((bookingsDTO.getStartTime().getField(DateField.YEAR)!=bookingsDTO.getEndTime().getField(DateField.YEAR))
            || bookingsDTO.getStartTime().getField(DateField.MONTH)!=bookingsDTO.getEndTime().getField(DateField.MONTH)
        ||bookingsDTO.getStartTime().getField(DateField.DAY_OF_MONTH)!=bookingsDTO.getEndTime().getField(DateField.DAY_OF_MONTH))
        {
            return Result.error(CODE_509,"预约时间段不能跨天");
        }
        //检测预约时间是否合法（早8点到晚10点）
        if(bookingsDTO.getStartTime().getField(DateField.HOUR_OF_DAY)<8
                ||bookingsDTO.getEndTime().getField(DateField.HOUR_OF_DAY)>22
                ||(bookingsDTO.getEndTime().getField(DateField.HOUR_OF_DAY)==22&&bookingsDTO.getEndTime().getField(DateField.MINUTE)>0))
        {
            return Result.error(CODE_510,"预约时间需要在早8点到晚10点");
        }

        //判断申请是否与分配表中记录冲突
        if(rb.collisionDetectionWithAllocations(bookingsDTO))
        {
            return Result.error(CODE_504,"申请场地已被分配");
        }
        else
        {
            if(rb.collisionDetectionWithBookings(bookingsDTO))
            {
                //与申请表产生冲突,则进行分配，如果分配成功则改变申请的场地ID，将改变后的申请记录插入申请表
                //查询空闲的场地
                Integer freeResourceId=rb.findFreeResourceId(bookingsDTO);
                if(freeResourceId==-1)
                {
                    return Result.error(CODE_506,"没有空闲场地，无法申请");
                }
                else
                {
                    bookingsDTO.setResourceId(freeResourceId); //改变申请的场地ID
                }
            }

            //将申请加入申请表
            Bookings bookings=rb.addBooking(bookingsDTO);
            return Result.success("成功插入申请记录",bookings);
        }
    }
}
