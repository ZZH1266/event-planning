package com.software.eventplanning.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.software.eventplanning.controller.dto.ResetDTO;
import com.software.eventplanning.entity.Users;

import javax.servlet.http.HttpSession;

public interface IResetService extends IService<Users> {

    Users reset(ResetDTO resetDTO, HttpSession session);
}
