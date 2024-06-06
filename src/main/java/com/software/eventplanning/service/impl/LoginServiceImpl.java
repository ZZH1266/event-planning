package com.software.eventplanning.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.software.eventplanning.common.Constants;
import com.software.eventplanning.controller.dto.LoginDTO;
import com.software.eventplanning.mapper.UsersMapper;
import com.software.eventplanning.entity.Users;
import com.software.eventplanning.exception.ServiceException;
import com.software.eventplanning.service.ILoginService;
import com.software.eventplanning.utils.TokenUtils;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl extends ServiceImpl<UsersMapper, Users> implements ILoginService {

    private static final Log LOG = Log.get();

    @Override
    public LoginDTO login(LoginDTO loginDTO) {
        Users one = getUserInfo(loginDTO);
        if (one != null) {
            BeanUtil.copyProperties(one, loginDTO, true);
            //设置token
            String token = TokenUtils.genToken(one.getId().toString(), one.getPassword());
            loginDTO.setToken(token);
            return loginDTO;
        } else {
            throw new ServiceException(Constants.CODE_600, "用户名或密码错误");
        }
    }

    // DTO类型数据转换为数据库所需的类型
    private Users getUserInfo(LoginDTO loginDTO) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", loginDTO.getUsername());
        queryWrapper.eq("password", loginDTO.getPassword());
        Users one;
        try {
            one = getOne(queryWrapper);
        } catch (Exception e) {
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        return one;
    }
}
