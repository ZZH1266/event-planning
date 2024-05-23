package com.software.eventplanning.mapper;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusPropertiesCustomizer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.software.eventplanning.controller.dto.AddActivitiesDTO;
import com.software.eventplanning.entity.Activities;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActivitiesMapper extends BaseMapper<Activities> {

}
