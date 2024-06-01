package com.software.eventplanning.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.software.eventplanning.controller.dto.LoginDTO;
import com.software.eventplanning.entity.Users;

public interface ILoginService extends IService<Users> {
    Users login(LoginDTO loginDTO);
}
