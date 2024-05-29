package com.software.eventplanning.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.software.eventplanning.common.Constants;
import com.software.eventplanning.controller.dto.NoticesTemplatesDTO;
import com.software.eventplanning.controller.dto.ResourcesDTO;
import com.software.eventplanning.entity.NoticesTemplates;
import com.software.eventplanning.entity.Resources;
import com.software.eventplanning.exception.ServiceException;
import com.software.eventplanning.mapper.NoticesTemplatesMapper;
import com.software.eventplanning.service.INoticesTemplatesService;
import org.springframework.stereotype.Service;

@Service
public class NoticesTemplatesServiceImpl extends ServiceImpl<NoticesTemplatesMapper, NoticesTemplates> implements INoticesTemplatesService {

    @Override
    public NoticesTemplates add(NoticesTemplatesDTO noticesTemplatesDTO) {
       QueryWrapper<NoticesTemplates> queryWrapper = new QueryWrapper<NoticesTemplates>();
       queryWrapper.eq("template_name", noticesTemplatesDTO.getTemplateName());
        NoticesTemplates one;
        one = this.getOne(queryWrapper);
        if (one == null) {
            one = new NoticesTemplates();
            BeanUtil.copyProperties(noticesTemplatesDTO, one, true);
            save(one);
        } else {
            throw new ServiceException(Constants.CODE_600, "该模板名称已经存在");
        }
        return one;
    }

}
