package com.software.eventplanning.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.software.eventplanning.controller.dto.NoticesTemplatesDTO;
import com.software.eventplanning.entity.NoticesTemplates;
import org.springframework.stereotype.Service;


public interface INoticesTemplatesService extends IService<NoticesTemplates> {
    NoticesTemplates add(NoticesTemplatesDTO noticesTemplatesDTO);
}
