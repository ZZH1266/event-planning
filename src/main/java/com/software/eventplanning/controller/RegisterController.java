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

@RestController
public class RegisterController {

    @Resource
    private IRegisterService emailService;

    @PostMapping("/sendEmail")
    @ResponseBody
    public String sendEmail(String email, HttpSession session) {
        emailService.sendEmail(email, session);
        return "success";
    }

    @PostMapping("/register")
    @ResponseBody
    public Result register(@RequestBody RegisterDTO registerDTO, HttpSession session) {
        String username = registerDTO.getUsername();
        String password = registerDTO.getPassword();
        String email = registerDTO.getEmail();
        String code = registerDTO.getCode();
        registerDTO.setRole(0);
        if (username == null || password == null || email == null || code == null) {
            return Result.error(Constants.CODE_400,"参数错误");
        }
        return Result.success(emailService.registered(registerDTO, session));
    }
}
