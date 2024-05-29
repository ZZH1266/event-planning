package com.software.eventplanning.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.software.eventplanning.controller.dto.BookingsDTO;
import com.software.eventplanning.entity.Bookings;
import com.software.eventplanning.mapper.BookingsMapper;
import com.software.eventplanning.service.IResourceBookingService;
import org.springframework.stereotype.Service;

@Service
public class ResourceBookingServiceImpl extends ServiceImpl<BookingsMapper, Bookings> implements IResourceBookingService {

    private static final Log LOG = Log.get();

    @Override
    public Bookings book(BookingsDTO bookingsDTO) {
        Bookings one=new Bookings();
        BeanUtil.copyProperties(bookingsDTO, one, true);
        save(one);
        return one;
    }



    @Override
    public Boolean collisiondetectionwithallocations(BookingsDTO bookingsDTO){
        return true;
    }


    @Override
    public Boolean collisiondetectionwithbookings(BookingsDTO bookingsDTO){
        return  true;
    }
}

