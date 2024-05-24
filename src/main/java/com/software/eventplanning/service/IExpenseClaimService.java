package com.software.eventplanning.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.software.eventplanning.controller.dto.ExpenseClaimDTO;
import com.software.eventplanning.entity.ExpenseClaim;

public interface IExpenseClaimService extends IService<ExpenseClaim> {
    ExpenseClaim submit(ExpenseClaimDTO expense_claimDTO);
}
