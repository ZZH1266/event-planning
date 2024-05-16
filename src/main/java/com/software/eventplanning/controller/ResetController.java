package com.software.eventplanning.controller;

import cn.hutool.core.util.StrUtil;
import com.software.eventplanning.common.Result;
import com.software.eventplanning.controller.dto.ResetDTO;
import com.software.eventplanning.exception.ServiceException;
import com.software.eventplanning.service.IResetService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
public class ResetController {
    @Resource
    private IResetService resetService;


    @PostMapping("/reset")
    public Result reset(@RequestBody ResetDTO resetDTO, HttpSession session) {
        String username = resetDTO.getUsername();
        String password = resetDTO.getPassword();
        String email = resetDTO.getEmail();
        String code = resetDTO.getCode();
        if (StrUtil.hasEmpty(username, password, email, code)) {
            throw new ServiceException(-1, "参数错误");
        }
        if (!resetDTO.getPassword().equals(resetDTO.getNewPassword())) {
            throw new ServiceException(-1, "两次密码不一致");
        }
        return Result.success(resetService.reset(resetDTO, session));
    }



}
