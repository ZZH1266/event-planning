package com.software.eventplanning.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.software.eventplanning.entity.Activities;
import com.software.eventplanning.entity.ExpenseClaim;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ExpenseClaimMapper extends BaseMapper<ExpenseClaim> {
    @Select("select count(*) from participants where user_id = #{userId} and activity_id = #{activityId}")
    int IsExpenseRight(@Param("userId")int userId,@Param("activityId")int activityId);
}
