package com.software.eventplanning.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.software.eventplanning.common.Constants;
import com.software.eventplanning.controller.dto.LoginDTO;
import com.software.eventplanning.mapper.UserMapper;
import com.software.eventplanning.entity.User;
import com.software.eventplanning.exception.ServiceException;
import com.software.eventplanning.service.ILoginService;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl extends ServiceImpl<UserMapper, User> implements ILoginService {

    private static final Log LOG = Log.get();

    @Override
    public LoginDTO login(LoginDTO loginDTO) {
        System.out.println("loginDTO: " + loginDTO);
        User one = getUserInfo(loginDTO);
        if (one != null) {
            BeanUtil.copyProperties(one, loginDTO, true);
            return loginDTO;
        } else {
            throw new ServiceException(Constants.CODE_600, "用户名或密码错误");
        }
    }

    private User getUserInfo(LoginDTO loginDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", loginDTO.getUsername());
        queryWrapper.eq("password", loginDTO.getPassword());
        User one;
        try {
            one = getOne(queryWrapper);
        } catch (Exception e) {
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        return one;
    }
}
