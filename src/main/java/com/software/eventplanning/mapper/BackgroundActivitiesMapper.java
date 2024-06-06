package com.software.eventplanning.mapper;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.software.eventplanning.entity.Activities;
import com.software.eventplanning.entity.Allocations;
import com.software.eventplanning.entity.Bookings;
import org.apache.ibatis.annotations.*;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface BackgroundActivitiesMapper extends BaseMapper<Activities> {
    @Delete("delete from activities where activity_id =#{activityId}")
    int deleteFromActivitiesById(@Param("activityId") int activityId);
    @Delete("delete from activity_reports where activity_id =#{activityId}")
    int deleteFromActivitiesReportsById(@Param("activityId")int activityId);

    @Delete("delete from activity_templates where activity_id =#{activityId}")
    int deleteFromActivitiesTemplatesById(@Param("activityId")int activityId);
    @Delete("delete from budgets where activity_id =#{activityId}")
    int deleteFromBudgetsById(@Param("activityId")int activityId);
    @Delete("delete from comments where activity_id =#{activityId}")
    int deleteFromCommentsById(@Param("activityId")int activityId);
    @Delete("delete from expense_claims where activity_id =#{activityId}")
    int deleteFromExpenseClaimsById(@Param("activityId")int activityId);
    @Delete("delete from feedback_analysis where activity_id =#{activityId}")
    int deleteFromFeedbackAnalysisById(@Param("activityId")int activityId);
    @Delete("delete from feedbacks where activity_id =#{activityId}")
    int deleteFromFeedbacksById(@Param("activityId")int activityId);
    //查询notification_logs表中所有指定activity_id的log_id
    @Select("select log_id from notification_logs where activity_id=#{activityId}")
    List<Integer> selectLogIdByActivityId(@Param("activityId") int activityId);
    @Delete("delete from notification_logs where activity_id =#{activityId}")
    int deleteFromNotificationLogsById(@Param("activityId")int activityId);
    @Delete("delete from notification_receptions where log_id=#{logId}")
    int deleteFromNotificationReceptionsById(@Param("logId") int logId);
    @Delete("delete from participant_applications where activity_id =#{activityId}")
    int deleteFromParticipantApplicationsById(@Param("activityId")int activityId);
    @Delete("delete from participants where activity_id =#{activityId}")
    int deleteFromParticipantsById(@Param("activityId")int activityId);
    @Delete("delete from resource_allocations where activity_id =#{activityId}")
    int deleteFromResourceAllocationsById(@Param("activityId")int activityId);
    @Delete("delete from resource_bookings where activity_id =#{activityId}")
    int deleteFromResourceBookingsById(@Param("activityId")int activityId);

    //下面开始为后台审批活动场地所用SQL语句
    @Select("select * from resource_bookings where booking_id=#{bookingId}")
    List<Bookings> findFromResourceBookingsByBookingId(@Param("bookingId") int bookingId); //问题：查询出的ResultSet中的DateTime类型无法给返回值中Bookings类型中的DateTIme类型赋值
    @Insert("insert into resource_allocations (activity_id,resource_id,resource_name,start_time,end_time,allocated_at) values (#{activityId},#{resourceId},#{resourceName},#{startTime},#{endTime},#{allocatedAt})")
    @SelectKey(statement = "select last_insert_id()",keyProperty = "allocationId",before = false,resultType = Integer.class) //获取主键
    int insertIntoResourceAllocation(Allocations allocations);
    @Update("update resource_bookings set status = '已审批' where booking_id=#{bookingId}") //同意审批
    int updateResourceBookingsStatusTrue(Bookings bookings);
    @Update("update resource_bookings set status ='拒绝'where booking_id=#{bookingId}")
    int updateResourceBookingsStatusFalse(Bookings bookings);

    //测试
    @Select("select start_time from resource_bookings where booking_id=4")
    List<DateTime>  test();
}
