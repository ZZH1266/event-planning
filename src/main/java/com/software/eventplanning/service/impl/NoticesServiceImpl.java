package com.software.eventplanning.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.software.eventplanning.controller.dto.NoticesDTO;
import com.software.eventplanning.controller.dto.NoticesReceptionsDTO;
import com.software.eventplanning.entity.NoticeReceptions;
import com.software.eventplanning.entity.Notices;
import com.software.eventplanning.entity.Resources;
import com.software.eventplanning.mapper.NoticesMapper;
import com.software.eventplanning.service.INoticesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.sql.Wrapper;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static com.baomidou.mybatisplus.core.toolkit.SystemClock.now;

@Service
public class NoticesServiceImpl extends ServiceImpl<NoticesMapper, Notices> implements INoticesService {

    private static final Log LOG = Log.get();
    @Autowired
    private NoticesMapper noticesMapper;

    @Override
    public Notices sendnotice(NoticesDTO noticesDTO){
        Notices one = new Notices();
        BeanUtil.copyProperties(noticesDTO, one, true);
        save(one);
        return one;
    }

    @Override
    public List<Notices> getnoticesByUserId(Integer userId){
        List<Notices> notices=noticesMapper.selectAllbyUserId(userId);
        return notices;
    }

    @Override
    public NoticeReceptions receivenotice(NoticesReceptionsDTO noticesReceptionsDTO){
        NoticeReceptions  one = new NoticeReceptions();
        BeanUtil.copyProperties(noticesReceptionsDTO, one, true);
        noticesMapper.addReception(one);
         return one;
    }
}
