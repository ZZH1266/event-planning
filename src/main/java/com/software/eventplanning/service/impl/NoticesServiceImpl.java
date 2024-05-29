package com.software.eventplanning.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.software.eventplanning.controller.dto.NoticesDTO;
import com.software.eventplanning.entity.Notices;
import com.software.eventplanning.mapper.NoticesMapper;
import com.software.eventplanning.service.INoticesService;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;

@Service
public class NoticesServiceImpl extends ServiceImpl<NoticesMapper, Notices> implements INoticesService {

    private static final Log LOG = Log.get();

    @Override
    public Notices sendnotice(NoticesDTO noticesDTO){
        Notices one = new Notices();
        BeanUtil.copyProperties(noticesDTO, one, true);
        save(one);
        return one;
    }
}
