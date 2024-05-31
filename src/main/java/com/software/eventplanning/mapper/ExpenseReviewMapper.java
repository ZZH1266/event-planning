package com.software.eventplanning.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.software.eventplanning.entity.ExpenseClaim;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ExpenseReviewMapper extends BaseMapper<ExpenseClaim> {

    // 查询一个用户待报销的所有申请ID
    @Select("select claim_id from expense_claim where user_id = #{userId}")
    List<Integer> selectClaimIdsByUserId(@Param(value = "userId") Integer userId);

}