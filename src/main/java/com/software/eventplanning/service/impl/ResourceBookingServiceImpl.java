package com.software.eventplanning.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
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
        //补充申请记录的resourceName字段
        one.setResourceName(bookingsMapper.findResourceNameById(bookingsDTO.getResourceId()));
        one.setStatus("申请中");
        save(one);
        return one;
    }

    @Override //申请与分配表间的冲突检测
    public Boolean collisionDetectionWithAllocations(BookingsDTO bookingsDTO)
    {
        Bookings bookings=new Bookings();
        BeanUtil.copyProperties(bookingsDTO,bookings,true);
        List<Allocations> allocationsList=bookingsMapper.findCollisionInAllocation(bookings.getResourceId(),bookings.getStartTime(),bookings.getEndTime());
        if(allocationsList.isEmpty())
        {

            return false; //不存在冲突
        }
        else
        {
            return true;
        }
    }

    @Override //申请表内的冲突检测
    public Boolean collisionDetectionWithBookings(BookingsDTO bookingsDTO)
    {
        Bookings bookings=new Bookings();
        BeanUtil.copyProperties(bookingsDTO,bookings,true);
        List<Bookings> bookingsList=bookingsMapper.findCollisionInBookings(bookings.getResourceId(),bookings.getStartTime(),bookings.getEndTime());
        if(bookingsList.isEmpty()) //为空则查询不到冲突记录
        {
            return false;//不存在冲突
        }
        else
        {
            return true; //存在冲突
        }
    }

    @Override //寻找一个空闲的场地，返回场地的ID号
    public Integer findFreeResourceId(BookingsDTO bookingsDTO)
    {
        Bookings bookings=new Bookings();
        BeanUtil.copyProperties(bookingsDTO,bookings,true);
        //寻找空闲的场地集合（初步寻找，不涉及排除申请表的内容）
        //寻找方法:先确定申请场地ID对应的场地种类，再确定该类的所有场地ID，再去除同时间在分配表的ID
        String resourceName= bookingsMapper.findResourceNameById(bookingsDTO.getResourceId()); //资源名称（类型）
        System.out.println(resourceName);
        List<Integer> FreeresourceIds=bookingsMapper.findResourceIdsByName(resourceName); //所有该资源类型下的所有资源编号
        List<Integer> resourceOcupyByName=bookingsMapper.findCollisionIdsInAlloByResourceName(resourceName,bookingsDTO.getStartTime(),bookingsDTO.getEndTime()); //通过资源类型（名称）得到分配表中所有该类资源已经被分配出的且冲突的场地编号
        FreeresourceIds.removeAll(resourceOcupyByName); //得到了空闲场地的集合，不涉及排除申请表的内容。
        //由于需要执行分配算法的为在申请表中冲突的表项，所以bookingsDTO中的resourceId也无法使用，需要进行排除
        List<Integer> resourceSelf=new ArrayList<>();
        resourceSelf.add(bookingsDTO.getResourceId());
        FreeresourceIds.removeAll(resourceSelf);//排除自身ID
        //此时得到所有可用的空闲场地，还需要进行申请表中已经成功申请的资源ID进行排除
        List<Integer> resourceBookingsByName=bookingsMapper.findCollisionIdsInBookingsByResourceName(resourceName,bookingsDTO.getStartTime(),bookingsDTO.getEndTime());//通过资源类型（名称）得到申请表中所有该类资源已经被申请的且冲突的场地编号
        FreeresourceIds.removeAll(resourceBookingsByName);
        if(FreeresourceIds.size()==0)
        {
            return -1; //证明没有空闲且没被申请的产地，无法分配
        }
        else
        {
            return FreeresourceIds.get(0); //返回分配的场地ID
        }
    }
}

