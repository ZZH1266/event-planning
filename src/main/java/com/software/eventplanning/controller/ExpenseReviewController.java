package com.software.eventplanning.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.software.eventplanning.common.Result;
import com.software.eventplanning.entity.ExpenseClaim;
import com.software.eventplanning.entity.ParticipantApplications;
import com.software.eventplanning.entity.Status;
import com.software.eventplanning.mapper.ExpenseReviewMapper;
import com.software.eventplanning.service.IExpenseClaimService;
import com.software.eventplanning.service.IExpenseReviewService;
import com.software.eventplanning.service.IParticipantApplicationService;
import com.software.eventplanning.service.IParticipantService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/expense_review")
public class ExpenseReviewController {

    @Resource
    private IExpenseReviewService expense_reviewService;

    /**
     * 处理申请
     * 分页查询所有申请
     */
    @GetMapping("/handle")
    public Result showAllClaims(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam Integer userId) {
        IPage<ExpenseClaim> page = new Page<>(pageNum, pageSize);
        QueryWrapper<ExpenseClaim> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        wrapper.eq("status", Status.Wating);
        return Result.success(expense_reviewService.page(page, wrapper));
    }
    /**
     * 处理申请
     * 通过申请
     */
    @PostMapping("/handle/apply")
    public Result applyExpenseClaim(@RequestBody ExpenseClaim expenseClaim){
        return Result.success(expense_reviewService.applyExpenseClaim(expenseClaim));
    }
    @PostMapping("/handle/deny")
    public Result denyExpenseClaim(@RequestBody ExpenseClaim expenseClaim)
    {
        return Result.success(expense_reviewService.denyExpenseClaim(expenseClaim));
    }
    //ExpenseClaim applyExpenseClaim(ExpenseClaim expenseClaim);

    //ExpenseClaim denyExpenseClaim(ExpenseClaim expenseClaim);

}
