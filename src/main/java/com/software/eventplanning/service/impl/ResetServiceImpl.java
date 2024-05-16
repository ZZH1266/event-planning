package com.software.eventplanning.service.impl;

import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.software.eventplanning.common.Constants;
import com.software.eventplanning.controller.dto.ResetDTO;
import com.software.eventplanning.entity.User;
import com.software.eventplanning.exception.ServiceException;
import com.software.eventplanning.mapper.UserMapper;
import com.software.eventplanning.service.IResetService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class ResetServiceImpl extends ServiceImpl<UserMapper, User> implements IResetService {

    private static final Log LOG = Log.get();

    @Override
    public User reset(ResetDTO resetDTO, HttpSession session) {
        String code = (String) session.getAttribute("code");
        String email = (String) session.getAttribute("email");
        String voCode = resetDTO.getCode();
        if(email == null){
            throw new ServiceException(-1, "请先获取验证码");
        }else if (!code.equals(voCode)) {
            throw new ServiceException(-1, "验证码错误");
        }
        User user = getUserInfo(resetDTO);
        if (user == null) {
            throw new ServiceException(-1, "用户不存在");
        } else {
            user.setPassword(resetDTO.getPassword());
            updateById(user);
            return user;
        }
    }

    private User getUserInfo(ResetDTO userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDTO.getUsername());
        queryWrapper.eq("email", userDTO.getEmail());
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
