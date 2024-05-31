package com.software.eventplanning.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.software.eventplanning.common.Constants;
import com.software.eventplanning.controller.dto.ExpenseClaimDTO;
import com.software.eventplanning.entity.ExpenseClaim;
import com.software.eventplanning.mapper.ExpenseClaimMapper;
import com.software.eventplanning.exception.ServiceException;
import com.software.eventplanning.service.IExpenseClaimService;
import org.springframework.stereotype.Service;

@Service
public class ExpenseClaimServiceImpl extends ServiceImpl<ExpenseClaimMapper, ExpenseClaim> implements IExpenseClaimService
{

    private static final Log LOG = Log.get();

    @Override
    public ExpenseClaim submit(ExpenseClaimDTO expense_claimDTO) {
        ExpenseClaim one = getExpenseClaimInfo(expense_claimDTO);
        if (one == null) {
            one = new ExpenseClaim();
            BeanUtil.copyProperties(expense_claimDTO,one, true);
            save(one);

        } else {
            throw new ServiceException(Constants.CODE_301,"已存在相同的报销申请！");
        }
        return one;
    }

    // DTO类型数据转换为数据库所需的类型
    private ExpenseClaim getExpenseClaimInfo(ExpenseClaimDTO expense_claimDTO) {
        QueryWrapper<ExpenseClaim> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("activity_id", expense_claimDTO.getActivityId())
                    .or()
                    .eq("user_id", expense_claimDTO.getUserId())
                    .or()
                    .eq("amount",expense_claimDTO.getAmount())
                    .or()
                    .eq("description",expense_claimDTO.getDescription());
        ExpenseClaim one;
        try {
            one = getOne(queryWrapper);
        } catch (Exception e) {
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        return one;
    }
}
