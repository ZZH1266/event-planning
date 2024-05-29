package com.software.eventplanning.controller;

import cn.hutool.core.util.StrUtil;
import com.software.eventplanning.common.Result;
import com.software.eventplanning.controller.dto.ActivitiesTemplateDTO;
import com.software.eventplanning.entity.Template;
import com.software.eventplanning.service.IAddActivitiesTemplateInfService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.software.eventplanning.common.Constants.CODE_403;
import static com.software.eventplanning.common.Constants.CODE_405;

//该控制器为添加活动附加信息（模板信息）用的
@RestController //包含controller和responsebody
public class AddActivitiesTemplateInfController {
    @Resource
    IAddActivitiesTemplateInfService addActivitiesTemplateInfService;
    @PostMapping("/AddActivitiesTemplateInf")
    public Result AddActivitiesTemplateInf(@RequestBody ActivitiesTemplateDTO activitiesTemplateDTO)
    {
        //开始检查输入是否正确
        Integer activityId= activitiesTemplateDTO.getActivityId();
        Integer templateId= activitiesTemplateDTO.getTemplateId();
        if(activityId==null || templateId==null)
        {
            return Result.error(CODE_403,"缺少活动ID或活动模板ID,系统异常");
        }
        Integer activitySize= activitiesTemplateDTO.getActivitySize();
        String placePlanToUse= activitiesTemplateDTO.getPlacePlanToUse();
        Template template=new Template();
        if(templateId==1) //代表为户外运动
        {
            String sportsKind=activitiesTemplateDTO.getSportsKind();
            String safetyOfficerName=activitiesTemplateDTO.getSafetyOfficerName();
            if(activitySize==null||StrUtil.isBlank(placePlanToUse)||StrUtil.isBlank(sportsKind)||StrUtil.isBlank(safetyOfficerName))
            {
                return Result.error(CODE_405,"缺少活动模板信息");
            }
            activitiesTemplateDTO.setIndoorKind(null);
            activitiesTemplateDTO.setHostName(null);
            activitiesTemplateDTO.setTeacherName(null);
            template=addActivitiesTemplateInfService.AddActivitiesTemplateInf(activitiesTemplateDTO);

        }
        else if(templateId==2) //代表室内运动
        {
            String indoorKind=activitiesTemplateDTO.getIndoorKind();
            String hostName=activitiesTemplateDTO.getHostName();
            String teacherName=activitiesTemplateDTO.getTeacherName();
            if(activitySize==null||StrUtil.isBlank(placePlanToUse)||StrUtil.isBlank(indoorKind)||StrUtil.isBlank(hostName)||StrUtil.isBlank(teacherName))
            {
                return Result.error(CODE_405,"缺少活动模板信息");
            }
            activitiesTemplateDTO.setSportsKind(null);
            activitiesTemplateDTO.setSafetyOfficerName(null);
            template=addActivitiesTemplateInfService.AddActivitiesTemplateInf(activitiesTemplateDTO);
        }

        return Result.success("成功插入活动模板信息",template);
    }
}
