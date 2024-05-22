package com.software.eventplanning.controller.dto;

import lombok.Data;

/**
 * 接受前端登录请求的参数
 */
@Data
public class LoginDTO {
    private String username;
    private String password;
}
