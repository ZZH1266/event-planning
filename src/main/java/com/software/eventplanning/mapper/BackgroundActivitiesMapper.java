package com.software.eventplanning.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.software.eventplanning.entity.Activities;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
}
