package com.software.eventplanning.controller.dto;

import lombok.Data;

@Data
public class BackgroundUserFindAllUsersDTO {
    Integer myId;//查询者的ID
    Integer userId;//查询的用户ID
    String userName;//查询的用户名
    int current;//当前查询的页码
    int size;//每页的记录数量
}
