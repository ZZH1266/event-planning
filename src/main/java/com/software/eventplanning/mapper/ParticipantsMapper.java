package com.software.eventplanning.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.software.eventplanning.entity.Participants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ParticipantsMapper extends BaseMapper<Participants> {

    @Select("select username from participants where activity_id = #{activityIdInt}")
    List<String> selectUserNameByActivityId(Integer activityIdInt);

    @Select("select count(*) from participants where activity_id = #{activityId} and username = #{username}")
    boolean hasUser(@Param(value = "activityId") Integer activityId,@Param(value = "username") String username);

    @Select("select activity_id from participants where user_id = #{userId}")
    List<Integer> selectActivityIdsByUserId(@Param(value = "userId") Integer userId);

    @Select("select activity_id from participants where user_id = #{userId} and role != '组织者'")
    List<Integer> selectJoinedActivityIdsByUserId(@Param(value = "userId") Integer userId);
}
