package com.software.eventplanning.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.software.eventplanning.common.Result;
import com.software.eventplanning.entity.Users;

public interface IBackgroundUserService extends IService<Users> {
    Result changeUserRole(int userId,int role);
    int getUserRole(int userId); //获取某一个用户的身份
}
