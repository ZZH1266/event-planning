package com.software.eventplanning.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.software.eventplanning.dao.UserMapper;
import com.software.eventplanning.entity.User;
import com.software.eventplanning.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
}
