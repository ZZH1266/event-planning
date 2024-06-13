package com.software.eventplanning.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.software.eventplanning.common.Result;
import com.software.eventplanning.controller.dto.BackgroundUserFindAllUsersDTO;
import com.software.eventplanning.entity.Users;
import com.software.eventplanning.mapper.BackgroundUserMapper;
import com.software.eventplanning.service.IBackgroundUserService;
import org.apache.catalina.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static com.software.eventplanning.common.Constants.*;

@Service
public class BackgroundUserServiceImpl extends ServiceImpl<BackgroundUserMapper, Users> implements IBackgroundUserService {
    @Autowired
    BackgroundUserMapper backgroundUserMapper;
    @Override
    public IPage<Users> findAllUsers(BackgroundUserFindAllUsersDTO backgroundUserFindAllUsersDTO) //分页查询所有用户
    {
        QueryWrapper<Users>queryWrapper=new QueryWrapper<>(); //按用户ID升序查询
        queryWrapper
                .orderByAsc("user_id");
        if(backgroundUserFindAllUsersDTO.getUserId()!=0)
        {
            queryWrapper
                    .eq("user_id",backgroundUserFindAllUsersDTO.getUserId());
        }
        if(!StringUtils.isBlank(backgroundUserFindAllUsersDTO.getUserName()))
        {
            queryWrapper
                    .like("username",backgroundUserFindAllUsersDTO.getUserName());
        }
        Page<Users> page=new Page<>(backgroundUserFindAllUsersDTO.getCurrent(),backgroundUserFindAllUsersDTO.getSize());
        IPage<Users> iPage=backgroundUserMapper.selectPage(page,queryWrapper);
        return iPage;
    }

    @Override
    public IPage<Users> findAllAdmins(int current,int size) //分页查询所有管理员
    {
        QueryWrapper<Users>queryWrapper=new QueryWrapper<>();
        queryWrapper
                .orderByAsc("user_id")
                .and(Wrapper->Wrapper.eq("role",1).or().eq("role",2));
        Page<Users> page=new Page<>(current,size);
        IPage<Users> iPage=backgroundUserMapper.selectPage(page,queryWrapper);
        return iPage;
    }
    //更改某一个用户的权限
    @Override
    public Result changeUserRole(int myId,int userId,int role)
    {
        //查询是否有该用户
        QueryWrapper queryWrapper=new QueryWrapper<>();
        queryWrapper
                .eq("user_id",userId);
        Users user=getOne(queryWrapper);
        if(user==null)
        {
            return Result.error(CODE_531,"该用户不存在");
        }
        if(role==2)
        {
            return Result.error(CODE_540,"不能设置新的超级管理员");
        }
        user.setRole(role); //改变权限
        user.setUpdatedTime(Timestamp.valueOf(LocalDateTime.now()));//更改记录中的最近修改时间
        boolean flag=update(user,queryWrapper);
        if(flag==true)
        {
            return Result.success("修改权限成功",user);
        }
        else
        {
            return Result.error(CODE_532,"修改权限操作失败");
        }
    }

    @Override
    public int getUserRole(int myId)
    {
        QueryWrapper<Users> queryWrapper=new QueryWrapper<>();
        queryWrapper
                .eq("user_id",myId);
        Users user=getOne(queryWrapper);
        return user.getRole();
    }
}
