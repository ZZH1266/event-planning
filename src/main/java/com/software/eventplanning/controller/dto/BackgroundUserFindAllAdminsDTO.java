package com.software.eventplanning.controller.dto;

import lombok.Data;

//查询所有管理员
@Data
public class BackgroundUserFindAllAdminsDTO {
    Integer myId;//查询者的ID
    Integer current;//当前查询页号
    Integer size;//每页显示数据条数
}
