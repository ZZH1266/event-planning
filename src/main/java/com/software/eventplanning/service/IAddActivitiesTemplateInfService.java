package com.software.eventplanning.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.software.eventplanning.controller.dto.ActivitiesTemplateDTO;
import com.software.eventplanning.entity.Template;
import org.springframework.stereotype.Service;


public interface IAddActivitiesTemplateInfService extends IService<Template> {
    Template AddActivitiesTemplateInf(ActivitiesTemplateDTO activitiesTemplateDTO);
}
