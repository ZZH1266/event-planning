package com.software.eventplanning.common;

public interface Constants {

    Integer CODE_200 = 200;
    Integer CODE_500 = 500;

    //创建活动时的错误
    Integer CODE_401 = 401; //创建活动时缺少必要的信息
    Integer CODE_402 = 402 ; //创建活动时活动名重复
    Integer CODE_403=403; //填入活动模板信息时缺少活动ID或模板ID
    Integer CODE_405=405; //填入活动模板时缺少模板信息
    Integer CODE_406=406; //创建活动模板时重复


    Integer CODE_400 = 400; //登录用户名或密码为空
    Integer CODE_600 = 600;
    Integer CODE_601 = 601;
    Integer CODE_602 = 602;
    Integer CODE_301 = 301; // 报销信息有空项
    Integer CODE_302 = 302;

}
