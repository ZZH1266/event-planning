package com.software.eventplanning.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.software.eventplanning.controller.dto.ActivitiesTemplateDTO;
import com.software.eventplanning.entity.Template;
import com.software.eventplanning.exception.ServiceException;
import com.software.eventplanning.mapper.ActivitiesTemplateMapper;
import com.software.eventplanning.service.IAddActivitiesTemplateInfService;
import org.springframework.stereotype.Service;

import static com.software.eventplanning.common.Constants.CODE_406;
import static com.software.eventplanning.common.Constants.CODE_500;

@Service
public class AddActivitiesTemplateInfImpl extends ServiceImpl<ActivitiesTemplateMapper, Template> implements IAddActivitiesTemplateInfService {

    private static final Log LOG= Log.get();
    //检测该活动之前是否创建过模板
    public boolean getOneActivityTemplate(Integer activityId)
    {
        QueryWrapper<Template> queryWrapper=new QueryWrapper<Template>();
        queryWrapper
                .eq("activity_id",activityId);
        Template template=new Template();
        try
        {
            template=getOne(queryWrapper);
            System.out.println(template);
        } catch (Exception e)
        {
            LOG.error(e);
            //查询数据库出现错误
            throw new ServiceException(CODE_500,"系统错误");
        }

        if(template==null)
        {
            //没查询到之前的记录，可以添加
            return true;
        }
        else
        {
            //不允许添加
            return false;
        }
    }
    @Override
    public Template AddActivitiesTemplateInf(ActivitiesTemplateDTO activitiesTemplateDTO)
    {
        //检测是否重复插入一个活动的多个模板信息
        if(getOneActivityTemplate(activitiesTemplateDTO.getActivityId()))
        {
            Template template=new Template(); //要传入数据库的模板信息
            BeanUtil.copyProperties(activitiesTemplateDTO,template,true); //ignoreCase为忽略源类与目标类对应属性大小写的差异
            boolean flag=save(template);
            if(flag==true)
            {
                return template;
            }
            else
            {
                return null;
            }
        }
        else
        {
            throw new ServiceException(CODE_406,"该项目已创建模板，请勿重复创建");
        }
    }
}