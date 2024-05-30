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
    @Select("select * from resource_allocations where resource_id = #{resourceId} " +
            "and ((start_time <  #{startTime} and #{startTime} < end_time)" +
            "or (start_time < #{endTime} and #{endTime} < end_time) " +
            "or (#{startTime} < start_time and #{endTime} > end_time))")
    List<Allocations> findCollisionInAllocation(@Param("resourceId") int resourceId, @Param("startTime") DateTime startTime, @Param("endTime") DateTime endTime);
}
