package com.software.eventplanning.mapper;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.software.eventplanning.entity.Activities;
import com.software.eventplanning.entity.Allocations;
import com.software.eventplanning.entity.Participants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface ScheduleMapper extends BaseMapper<Participants> {

    //查询一个用户的所有活动 按时间排序
    @Select("select a.* from participants As p " +
            "JOIN activities As a On a.activity_id=p.activity_id " +
            "where p.user_id=#{userId} " +
            "Order by a.start_time ")
    List<Activities> getActivitiesFromParticipantsByUserId(@Param("userId") Integer userId);

    //创建活动时的冲突检测
    @Select("SELECT a.* FROM activities AS a JOIN participants AS p ON a.activity_id = p.activity_id WHERE p.user_id = #{userId} AND ((a.start_time <= #{endTime} AND a.end_time >= #{startTime}))")
    List<Activities> findCollisionInActivity(@Param("userId") int userId, @Param("startTime") DateTime startTime, @Param("endTime") DateTime endTime);

    //加入活动时的冲突检测
    @Select("SELECT a2.* FROM activities AS a1 JOIN participants AS p ON a1.activity_id = p.activity_id JOIN activities AS a2 ON a2.activity_id = #{activityId} WHERE p.user_id = #{userId} AND p.activity_id != #{activityId} AND ((a2.start_time < a1.end_time AND a2.end_time > a1.start_time))")
    List<Activities> findCollisionInParticipants(@Param("activityId") int activityId, @Param("userId") int userId);


}