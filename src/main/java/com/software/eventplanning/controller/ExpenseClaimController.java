package com.software.eventplanning.controller;

import cn.hutool.core.util.StrUtil;
import com.software.eventplanning.common.Constants;
import com.software.eventplanning.common.Result;
import com.software.eventplanning.controller.dto.ExpenseClaimDTO;
import com.software.eventplanning.service.IExpenseClaimService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ExpenseClaimController {

    @Resource
    private IExpenseClaimService expense_claimService;

    @PostMapping("/expense_claim")
    public Result t(@RequestBody ExpenseClaimDTO expense_claimDTO) {
        Integer userId = expense_claimDTO.getUserId();
        Integer activityId = expense_claimDTO.getActivityId();
        Integer amount = expense_claimDTO.getAmount();
        String description = expense_claimDTO.getDescription();
        if ( userId==null || activityId == null || amount==null || StrUtil.isBlank(description)) {
            return Result.error(Constants.CODE_301, "报销申请有空项！");
        }
        else {
            return Result.success(expense_claimService.submit(expense_claimDTO));
        }
    }

}
