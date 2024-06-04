package com.software.eventplanning.controller.dto;

import lombok.Data;

//后台管理员改变用户权限用的DTO
@Data
public class BackgroundUserChangeRoleDTO {
    int myId;//当前账号登录者(试图进行用户操作的人)的ID
    int userId; //操作的用户ID
    int role; //要变成的身份，0为普通用户，1为系统管理员
}
