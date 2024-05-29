package com.software.eventplanning.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.software.eventplanning.common.Constants;
import com.software.eventplanning.controller.dto.ReportsDTO;
import com.software.eventplanning.entity.Reports;
import com.software.eventplanning.exception.ServiceException;
import com.software.eventplanning.mapper.ReportsMapper;
import com.software.eventplanning.service.IReportsService;
import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReportsServiceImpl extends ServiceImpl<ReportsMapper, Reports> implements IReportsService {
    private static final Log LOG = Log.get();

    @Override
    public Reports generated(ReportsDTO reportsDTO) {
        QueryWrapper<Reports> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("activity_id", reportsDTO.getActivityId());
        Reports one = getOne(queryWrapper);
        if(one==null) {
            one =new Reports();
            BeanUtil.copyProperties(reportsDTO, one, true);
            save(one);
        }else {
            throw new ServiceException(Constants.CODE_400, "已经生成过活动报告了");
        }
        return one;
    }
}
