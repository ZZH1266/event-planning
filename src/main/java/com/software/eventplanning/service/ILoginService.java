package com.software.eventplanning.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.software.eventplanning.controller.dto.LoginDTO;
import com.software.eventplanning.entity.User;

public interface ILoginService extends IService<User> {
    LoginDTO login(LoginDTO loginDTO);
}
