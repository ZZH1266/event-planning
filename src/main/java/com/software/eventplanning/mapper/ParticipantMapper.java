package com.software.eventplanning.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.software.eventplanning.entity.Participants;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ParticipantMapper extends BaseMapper<Participants> {
}
