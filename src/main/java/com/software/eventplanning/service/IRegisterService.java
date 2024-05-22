package com.software.eventplanning.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.software.eventplanning.controller.dto.RegisterDTO;
import com.software.eventplanning.entity.Users;

import javax.servlet.http.HttpSession;

public interface IRegisterService extends IService<Users> {
    boolean sendEmail(String email, HttpSession session);

    Users registered(RegisterDTO registerDTO, HttpSession session);
}
