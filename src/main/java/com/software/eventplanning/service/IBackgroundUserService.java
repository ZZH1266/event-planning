package com.software.eventplanning.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.software.eventplanning.common.Result;
import com.software.eventplanning.entity.Users;

public interface IBackgroundUserService extends IService<Users> {
    IPage<Users> findAllUsers(int current,int size); //分页查所有用户
    IPage<Users> findAllAdmins(int current,int size);//分页查所有管理员
    Result changeUserRole(int myId,int userId,int role);
    int getUserRole(int userId); //获取某一个用户的身份
}
