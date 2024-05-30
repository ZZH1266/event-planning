package com.software.eventplanning.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.software.eventplanning.controller.dto.BookingsDTO;
import com.software.eventplanning.entity.Allocations;
import com.software.eventplanning.entity.Bookings;
import com.software.eventplanning.mapper.BookingsMapper;
import com.software.eventplanning.service.IResourceBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResourceBookingServiceImpl extends ServiceImpl<BookingsMapper, Bookings> implements IResourceBookingService {

    @Autowired
    BookingsMapper bookingsMapper;
    private static final Log LOG = Log.get();

    //向申请表添加一个记录
    @Override
    public Bookings addBooking(BookingsDTO bookingsDTO) {
        Bookings one=new Bookings();
        BeanUtil.copyProperties(bookingsDTO, one, true);
        save(one);
        return one;
    }



    @Override
    public Boolean collisionDetectionWithAllocations(BookingsDTO bookingsDTO)
    {
        Bookings bookings=new Bookings();
        BeanUtil.copyProperties(bookingsDTO,bookings,true);
        List<Allocations> allocationsList=bookingsMapper.findCollisionInAllocation(bookings.getResourceId(),bookings.getStartTime(),bookings.getEndTime());
        if(allocationsList.isEmpty())
        {
            System.out.println(allocationsList);
            return false; //不存在冲突
        }
        else
        {
            return true;
        }
    }

    @Override
    public Boolean collisionDetectionWithBookings(BookingsDTO bookingsDTO){
        return  true;
    }
}

