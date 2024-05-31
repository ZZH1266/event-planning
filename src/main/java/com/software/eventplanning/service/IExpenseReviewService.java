package com.software.eventplanning.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.software.eventplanning.entity.ExpenseClaim;


public interface IExpenseReviewService extends IService<ExpenseClaim> {

    ExpenseClaim applyExpenseClaim(ExpenseClaim expenseClaim);

    ExpenseClaim denyExpenseClaim(ExpenseClaim expenseClaim);
}