package com.software.eventplanning.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.software.eventplanning.controller.dto.FeedbacksDTO;
import com.software.eventplanning.entity.Feedbacks;

public interface IFeedbacksService extends IService<Feedbacks> {
    Feedbacks submitted(FeedbacksDTO feedbacksDTO);
}
