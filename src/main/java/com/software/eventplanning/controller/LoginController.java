package com.software.eventplanning.controller;

import cn.hutool.core.util.StrUtil;
import com.software.eventplanning.common.Constants;
import com.software.eventplanning.common.Result;
import com.software.eventplanning.controller.dto.LoginDTO;
import com.software.eventplanning.entity.Users;
import com.software.eventplanning.service.ILoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class LoginController {

    @Resource
    private ILoginService userService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400, "用户或密码为空");
        }
        Users dto = userService.login(loginDTO);
      //
        return Result.success(dto);
    }

}
