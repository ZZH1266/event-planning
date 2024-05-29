package com.software.eventplanning.controller;

import cn.hutool.core.util.StrUtil;
import com.software.eventplanning.common.Result;
import com.software.eventplanning.controller.dto.AddActivitiesDTO;
import com.software.eventplanning.entity.Activities;
import com.software.eventplanning.service.IAddActivitiesService;
import org.apache.commons.lang.ObjectUtils;
import org.apache.ibatis.jdbc.Null;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Date;
import java.sql.Timestamp;

import static com.software.eventplanning.common.Constants.CODE_401;

@RestController
public class AddActivitiesController {
    @Resource
    private IAddActivitiesService addActivitiesService;

    @PostMapping("/AddActivities")
    @ResponseBody
    public Result AddActivities(@RequestBody AddActivitiesDTO addActivitiesDTO)
    {
        //分离对象的各个参数
        String activityName= addActivitiesDTO.getActivityName();
        String description= addActivitiesDTO.getDescription();
        Integer creatorId= addActivitiesDTO.getCreatorId();
        Integer templateId= addActivitiesDTO.getTemplateId();
        //下面的参数可以为空
        Date startTime=addActivitiesDTO.getStartTime();
        Date endTime=addActivitiesDTO.getEndTime();
        Timestamp createdTime=addActivitiesDTO.getCreatedTime();
        Timestamp updatedTime=addActivitiesDTO.getUpdatedTime();

        if(StrUtil.isBlank(activityName)||StrUtil.isBlank(description)||creatorId== null ||templateId==null)
        {
            return Result.error(CODE_401,"缺少创建活动必须的信息");
        }
        Activities activities=new Activities();
        //添加活动
        activities=addActivitiesService.AddActivities(addActivitiesDTO);

        return Result.success("成功创建活动",activities); //返回正确信息
    }
}
