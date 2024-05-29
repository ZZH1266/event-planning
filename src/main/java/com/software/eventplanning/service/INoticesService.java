package com.software.eventplanning.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.software.eventplanning.controller.dto.NoticesDTO;
import com.software.eventplanning.entity.Notices;

public interface INoticesService extends IService<Notices> {
    Notices sendnotice(NoticesDTO noticesDTO);
}
