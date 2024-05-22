package com.software.eventplanning.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.software.eventplanning.controller.dto.ResetDTO;
import com.software.eventplanning.entity.User;

import javax.servlet.http.HttpSession;

public interface IResetService extends IService<User> {

    User reset(ResetDTO resetDTO, HttpSession session);
}
