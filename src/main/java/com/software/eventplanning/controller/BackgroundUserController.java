package com.software.eventplanning.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.software.eventplanning.common.Result;
import com.software.eventplanning.controller.dto.BackgroundUserChangeRoleDTO;
import com.software.eventplanning.controller.dto.BackgroundUserFindAllUsersDTO;
import com.software.eventplanning.entity.Users;
import com.software.eventplanning.service.IBackgroundUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.software.eventplanning.common.Constants.*;

//后台管理用户的控制类，负责后台对用户的(增)删改查以及对用户身份的调整操作
@RestController
public class BackgroundUserController {
    @Resource
    public IBackgroundUserService iBackgroundUserService;
    //分页查询所有用户信息
    @PostMapping("/background/user/findAllUsers")
    public IPage<Users> findAllUsers(@RequestBody BackgroundUserFindAllUsersDTO backgroundUserFindAllUsersDTO)
    {
        int current= backgroundUserFindAllUsersDTO.getCurrent(); //当前查询的页数
        int size= backgroundUserFindAllUsersDTO.getSize(); //每页显示的记录数
        IPage<Users> iPage=iBackgroundUserService.findAllUsers(current,size);
        return iPage;
    }

    //改变用户权限
    @PostMapping("/background/user/changeRole")
    public Result changeRole(@RequestBody BackgroundUserChangeRoleDTO backgroundUserChangeRoleDTO) //userId为要修改的用户 role为修改为的角色(0为普通用户，1为系统管理员)
    {
        int myId= backgroundUserChangeRoleDTO.getMyId();//获取操作者的Id
        if(myId<=0)
        {
            return Result.error(CODE_533,"请确认您是否正确登陆");
        }
        if(iBackgroundUserService.getUserRole(myId)==0)
        {
            return Result.error(CODE_534,"您无权进行修改");
        }
        int userId=backgroundUserChangeRoleDTO.getUserId();
        int role= backgroundUserChangeRoleDTO.getRole();
        if(userId<=0)
        {
            return Result.error(CODE_530,"输入的用户ID不合法");
        }
        if(myId==userId)
        {
            return Result.error(CODE_535,"您无法修改您自身的权限");
        }
        Result result=iBackgroundUserService.changeUserRole(userId,role);
        return result;
    }
}

