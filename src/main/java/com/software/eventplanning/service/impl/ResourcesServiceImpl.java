package com.software.eventplanning.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.software.eventplanning.common.Constants;
import com.software.eventplanning.controller.dto.ResourcesDTO;
import com.software.eventplanning.entity.Resources;
import com.software.eventplanning.entity.Users;
import com.software.eventplanning.exception.ServiceException;
import com.software.eventplanning.mapper.ResourcesMapper;
import com.software.eventplanning.service.IResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourcesServiceImpl extends ServiceImpl<ResourcesMapper, Resources> implements IResourcesService {

    private static final Log LOG = Log.get();
    @Autowired
    private ResourcesMapper rm;
    @Override
    public Resources add(ResourcesDTO resourcesDTO) {

        Resources one = getResourceInfo(resourcesDTO);
        if (one == null) {
            one = new Resources();
            BeanUtil.copyProperties(resourcesDTO, one, true);
            save(one);
        } else {
            throw new ServiceException(Constants.CODE_600, "该资源名称已经存在");
        }
        return one;
    }

    private Resources getResourceInfo(ResourcesDTO resourcesDTO) {
        QueryWrapper<Resources> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("resource_name", resourcesDTO.getResourceName());
        Resources one;
        try {
            one = getOne(queryWrapper);
        } catch (Exception e) {
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        return one;
    }

    @Override
    public Resources update(ResourcesDTO resourcesDTO,Integer resourceId) {

        Resources one = getResourceById(resourcesDTO,resourceId);
        if (one == null) {
            throw new ServiceException(-1, "资源不存在");
        } else {
            one.setDescription(resourcesDTO.getDescription());
            one.setResourceType(resourcesDTO.getResourceType());
            one.setResourceName(resourcesDTO.getResourceName());
            one.setStatus(resourcesDTO.getStatus());
            updateById(one);
            return one;
        }

    }



    private Resources getResourceById(ResourcesDTO resourcesDTO,Integer resourceId) {
        QueryWrapper<Resources> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("resource_id",resourceId);
        Resources one;
        try{
            one =getOne(queryWrapper);
        }catch (Exception e){
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        return one;
    }

    @Override
    public Boolean delete(Integer resourceId) {
        rm.deleteResourceBookingsByResourceId(resourceId) ;
        rm.deleteResourceAllocationsByResourceId(resourceId);
        return removeById(resourceId);
    }

    @Override
    public Resources getresourceById(Integer resourceId) {
        QueryWrapper<Resources> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("resource_id", resourceId);
        Resources one;
        try{
            one = getOne(queryWrapper);
        }catch (Exception e){
            LOG.error(e);
            throw new ServiceException(500,"系统错误");
        }
        return one;
    }
}
