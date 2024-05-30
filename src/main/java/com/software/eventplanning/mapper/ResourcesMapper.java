package com.software.eventplanning.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.software.eventplanning.entity.Resources;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ResourcesMapper extends BaseMapper<Resources> {
    @Delete("delete from resource_bookings where resource_id = #{resourceId}")
    boolean deleteResourceBookingsByResourceId(Integer resourceId);

    @Delete("delete from resource_allocations where resource_id = #{resourceId}")
    boolean deleteResourceAllocationsByResourceId(Integer resourceId);

}
