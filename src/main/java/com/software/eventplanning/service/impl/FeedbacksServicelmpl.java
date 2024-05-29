package com.software.eventplanning.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.software.eventplanning.common.Constants;
import com.software.eventplanning.controller.dto.FeedbacksDTO;
import com.software.eventplanning.entity.Feedbacks;
import com.software.eventplanning.entity.Users;
import com.software.eventplanning.exception.ServiceException;
import com.software.eventplanning.mapper.FeedbacksMapper;
import com.software.eventplanning.service.IFeedbacksService;
import org.springframework.stereotype.Service;

@Service
public class FeedbacksServicelmpl extends ServiceImpl<FeedbacksMapper,Feedbacks> implements IFeedbacksService {
    private static final Log LOG = Log.get();

    @Override
    public Feedbacks submitted(FeedbacksDTO feedbacksDTO) {
              //将反馈信息存入数据库
        Feedbacks one = getFeedbackInfo(feedbacksDTO);
        if (one == null) {
            one = new Feedbacks();
            BeanUtil.copyProperties(feedbacksDTO, one, true);
            save(one);
        } else {
            throw new ServiceException(Constants.CODE_400, "不能重复提交活动反馈");
        }
        return one;

    }

    private Feedbacks getFeedbackInfo(FeedbacksDTO feedbacksDTO){
        QueryWrapper<Feedbacks> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",feedbacksDTO.getUserId());
        Feedbacks one;
        try {
            one = getOne(queryWrapper);
        } catch (Exception e) {
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        return one;

    }
}
