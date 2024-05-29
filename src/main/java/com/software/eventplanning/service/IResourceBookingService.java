package com.software.eventplanning.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.software.eventplanning.controller.dto.BookingsDTO;
import com.software.eventplanning.entity.Bookings;

public interface IResourceBookingService extends IService<Bookings> {
    Bookings book(BookingsDTO bookingsDTO);
    Boolean  collisiondetectionwithallocations(BookingsDTO bookingsDTO); //和已有分配表冲突
    Boolean  collisiondetectionwithbookings(BookingsDTO bookingsDTO);   //和已有申请冲突
}
