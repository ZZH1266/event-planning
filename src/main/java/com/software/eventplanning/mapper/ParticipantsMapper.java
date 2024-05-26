package com.software.eventplanning.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.software.eventplanning.entity.Participants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ParticipantsMapper extends BaseMapper<Participants> {

    @Select("select username from participants where activity_id = #{activityIdInt}")
    List<String> selectUserNameByActivityId(Integer activityIdInt);
}
