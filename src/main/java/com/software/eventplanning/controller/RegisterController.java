package com.software.eventplanning.controller;

import com.software.eventplanning.common.Constants;
import com.software.eventplanning.common.Result;
import com.software.eventplanning.controller.dto.RegisterDTO;
import com.software.eventplanning.service.IRegisterService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.regex.Pattern;

@RestController
public class RegisterController {

    @Resource
    private IRegisterService emailService;

    @PostMapping("/sendEmail")
    @ResponseBody
    public Result sendEmail(String email, HttpSession session) {
        if (!Pattern.matches("^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$", email)) {
            return Result.error(Constants.CODE_400,"邮箱格式错误");
        }
        emailService.sendEmail(email, session);
        return Result.success();
    }

    @PostMapping("/register")
    @ResponseBody
    public Result register(@RequestBody RegisterDTO registerDTO, HttpSession session) {
        String username = registerDTO.getUsername();
        String password = registerDTO.getPassword();
        String email = registerDTO.getEmail();
        String code = registerDTO.getCode();
        //用户名正则，4到16位（字母，数字，下划线，减号）
        if (!Pattern.matches("^[a-zA-Z0-9_-]{4,16}$", username)) {
            return Result.error(Constants.CODE_400,"用户名格式错误");
        }

        if (!Pattern.matches("^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$", email)) {
            return Result.error(Constants.CODE_400,"邮箱格式错误");
        }

        registerDTO.setRole(0);
        if (username == null || password == null || email == null || code == null) {
            return Result.error(Constants.CODE_400,"参数错误");
        }
        return Result.success(emailService.registered(registerDTO, session));
    }
}
