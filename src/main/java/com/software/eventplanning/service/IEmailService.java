package com.software.eventplanning.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.software.eventplanning.controller.dto.RegisterDTO;
import com.software.eventplanning.entity.User;

import javax.servlet.http.HttpSession;

public interface IEmailService extends IService<User> {
    boolean sendEmail(String email, HttpSession session);

    User registered(RegisterDTO registerDTO, HttpSession session);
}
