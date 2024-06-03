package com.software.eventplanning.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.software.eventplanning.controller.dto.NoticesDTO;
import com.software.eventplanning.controller.dto.NoticesReceptionsDTO;
import com.software.eventplanning.entity.NoticeReceptions;
import com.software.eventplanning.entity.Notices;

import java.util.List;

public interface INoticesService extends IService<Notices> {
    Notices sendnotice(NoticesDTO noticesDTO);

    List<Notices> getnoticesByUserId(Integer userId);

    NoticeReceptions receivenotice(NoticesReceptionsDTO noticesReceptionsDTO);
}
