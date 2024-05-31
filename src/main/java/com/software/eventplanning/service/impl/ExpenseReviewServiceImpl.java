package com.software.eventplanning.service.impl;


import cn.hutool.log.Log;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.software.eventplanning.common.Constants;
import com.software.eventplanning.controller.dto.ExpenseClaimDTO;
import com.software.eventplanning.controller.dto.ParticipantsDTO;
import com.software.eventplanning.entity.ExpenseClaim;

import com.software.eventplanning.entity.Participants;
import com.software.eventplanning.entity.Status;
import com.software.eventplanning.exception.ServiceException;
import com.software.eventplanning.mapper.ExpenseReviewMapper;

import com.software.eventplanning.mapper.ParticipantsMapper;
import com.software.eventplanning.service.IExpenseReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseReviewServiceImpl extends ServiceImpl<ExpenseReviewMapper, ExpenseClaim> implements IExpenseReviewService
{

    @Autowired
    private ExpenseReviewMapper expenseReviewMapper;

    @Override
    public ExpenseClaim applyExpenseClaim(ExpenseClaim expenseClaim)
    {
        expenseClaim.setStatus(Status.Approved);
        updateById(expenseClaim);
        return expenseClaim;
    }

    @Override
    public ExpenseClaim denyExpenseClaim(ExpenseClaim expenseClaim)
    {
        expenseClaim.setStatus(Status.Denied);
        updateById(expenseClaim);
        return expenseClaim;
    }



}
