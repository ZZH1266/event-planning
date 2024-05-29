package com.software.eventplanning.controller;

import com.software.eventplanning.common.Constants;
import com.software.eventplanning.common.Result;
import com.software.eventplanning.controller.dto.FeedbacksDTO;
import com.software.eventplanning.controller.dto.RegisterDTO;
import com.software.eventplanning.service.IFeedbacksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class FeedbacksController {

    @Autowired
     private IFeedbacksService feedbacksService;

    @PostMapping("/feedbacksubmitted")
    @ResponseBody
    public Result submit(@RequestBody FeedbacksDTO feedbacksDTO) {
        Integer rating = feedbacksDTO.getRating();
        String context = feedbacksDTO.getContext();
        if (rating == null ||context == null) {
            return Result.error(Constants.CODE_400,"内容或者评分为空");
        }
        return Result.success(feedbacksService.submitted(feedbacksDTO));
    }

}
