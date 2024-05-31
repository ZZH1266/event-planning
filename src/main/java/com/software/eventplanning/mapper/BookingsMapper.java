package com.software.eventplanning.mapper;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.software.eventplanning.entity.Allocations;
import com.software.eventplanning.entity.Bookings;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookingsMapper extends BaseMapper<Bookings> {
    //在分配表中查找冲突记录
    @Select("select * from resource_allocations where resource_id = #{resourceId} " +
            "and ((start_time <=  #{startTime} and #{startTime} < end_time)" +
            "or (start_time < #{endTime} and #{endTime} <= end_time) " +
            "or (#{startTime} < start_time and #{endTime} > end_time))")
    List<Allocations> findCollisionInAllocation(@Param("resourceId") int resourceId, @Param("startTime") DateTime startTime, @Param("endTime") DateTime endTime);

    //在预定表中查找冲突预定
    @Select("select resource_id from resource_bookings where resource_id = #{resourceId} " +
            "and ((start_time <=  #{startTime} and #{startTime} < end_time)" +
            "or (start_time < #{endTime} and #{endTime} <= end_time) " +
            "or (#{startTime} < start_time and #{endTime} > end_time))")
    List<Bookings> findCollisionInBookings(@Param("resourceId") int resourceId, @Param("startTime") DateTime startTime, @Param("endTime") DateTime endTime);

    //根据资源名称查询资源类型
    @Select("select resource_name from resources where resource_id=#{resourceId}")
    String findResourceNameById(@Param("resourceId") int resourceId);

    //根据资源类型确定该资源类型下的所有资源的ID
    @Select("select resource_id from resources where resource_name = #{resourceName}")
    List<Integer> findResourceIdsByName(@Param("resourceName") String resourceName);

    //通过资源类型寻找该资源类型在特定时间段被占用的场地的编号（在分配表中查询）
    @Select("select resource_id from resource_allocations where resource_name = #{resourceName} " +
            "and ((start_time <=  #{startTime} and #{startTime} < end_time)" +
            "or (start_time < #{endTime} and #{endTime} <= end_time) " +
            "or (#{startTime} < start_time and #{endTime} > end_time))")
    List<Integer> findCollisionIdsInAlloByResourceName(@Param("resourceName") String resourceName, @Param("startTime") DateTime startTime, @Param("endTime") DateTime endTime);

    //根据资源类型寻找该资源类型在特定时间段被申请的场地的编号（在申请表中查询）
    @Select("select resource_id from resource_bookings where resource_name = #{resourceName} " +
            "and ((start_time <=  #{startTime} and #{startTime} < end_time)" +
            "or (start_time < #{endTime} and #{endTime} <= end_time) " +
            "or (#{startTime} < start_time and #{endTime} > end_time))")
    List<Integer> findCollisionIdsInBookingsByResourceName(@Param("resourceName") String resourceName, @Param("startTime") DateTime startTime, @Param("endTime") DateTime endTime);
}
