package com.software.eventplanning.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.software.eventplanning.controller.dto.BookingsDTO;
import com.software.eventplanning.entity.Bookings;

public interface IResourceBookingService extends IService<Bookings> {
    Bookings addBooking(BookingsDTO bookingsDTO);
    Boolean collisionDetectionWithAllocations(BookingsDTO bookingsDTO); //和已有分配表冲突
    Boolean collisionDetectionWithBookings(BookingsDTO bookingsDTO);   //和已有申请冲突
    Integer findFreeResourceId(BookingsDTO bookingsDTO); //寻找一个空闲的场地，返回场地的ID号
}
