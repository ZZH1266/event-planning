package com.software.eventplanning.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.software.eventplanning.common.Constants;
import com.software.eventplanning.controller.dto.AddActivitiesDTO;
import com.software.eventplanning.entity.Activities;
import com.software.eventplanning.entity.Participants;
import com.software.eventplanning.entity.Users;
import com.software.eventplanning.exception.ServiceException;
import com.software.eventplanning.mapper.ActivitiesMapper;
import com.software.eventplanning.mapper.ParticipantsMapper;
import com.software.eventplanning.service.IAddActivitiesService;
import com.software.eventplanning.service.ILoginService;
import com.software.eventplanning.service.IParticipantService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddActivitiesServiceImpl extends ServiceImpl<ActivitiesMapper, Activities> implements IAddActivitiesService {

    @Autowired
    ParticipantsMapper participantsMapper;
    @Autowired
    ILoginService iLoginService;
    @Autowired
    IParticipantService iParticipantService;

    private static final Log LOG= Log.get();
    @Override
    public Activities AddActivities(AddActivitiesDTO addActivitiesDTO)
    {
        Activities activity;
        if(getOneActivity(addActivitiesDTO))
        {
            activity=new Activities(); //真正要传入数据库中的对象
            BeanUtil.copyProperties(addActivitiesDTO,activity,true);
            save(activity); //activity_id在创建活动后会回传给activity对象


            Participants participants=new Participants(); //插入活动用户关系表
            participants.setActivityId(activity.getActivityId());
            participants.setUserId(activity.getCreatorId());
            participants.setActivityRole("组织者");

            //根据UserID查询用户名
            Integer userId= addActivitiesDTO.getCreatorId();
            QueryWrapper<Users> queryWrapper=new QueryWrapper<Users>();
            queryWrapper
                    .eq("user_id",userId);
            Users user=iLoginService.getOne(queryWrapper);

            participants.setUsername(user.getUsername());
            iParticipantService.save(participants);

        }
        else
        {
            throw new ServiceException(Constants.CODE_402,"活动名重复,请更换活动名");
        }
        return activity;
    }


    //展示活动
    @Override
    public IPage<Activities> showAllActivities(Integer pageNum, Integer pageSize, String activityName, Integer userId)
    {
        IPage<Activities> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Activities> wrapper = new QueryWrapper<>();
        // 按照活动id降序排列
        wrapper.orderByDesc("activity_id");
        // 根据activityName模糊查询活动
        wrapper.like(Strings.isNotEmpty(activityName), "activity_name", activityName);

        // 根据userId查询用户已经参加的活动
        List<Integer> activityIds = participantsMapper.selectActivityIdsByUserId(userId);
        for (Integer activityId : activityIds) {
            wrapper.ne("activity_id", activityId);
        }

        return page(page, wrapper);
    }

    //用于检测创建的活动名是否重复
    private Boolean getOneActivity(AddActivitiesDTO addActivitiesDTO)
    {
        QueryWrapper<Activities> queryWrapper=new QueryWrapper<Activities>();
        queryWrapper
                .eq("activity_name",addActivitiesDTO.getActivityName());//表示不能有重复的活动名
        Activities activity=new Activities();
        try {//执行查询操作
            activity=getOne(queryWrapper);
            System.out.println(activity);
        } catch (Exception e)
        {//查询数据库时出现错误
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }

        if(activity==null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
