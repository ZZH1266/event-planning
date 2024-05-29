package com.software.eventplanning.service;

import com.software.eventplanning.controller.dto.ResourcesDTO;
import com.software.eventplanning.entity.Resources;

public interface IResourcesService {

    Resources add(ResourcesDTO resourcesDTO);

    Resources update(ResourcesDTO resourcesDTO,Integer resourcesId);

    Boolean delete(Integer resourceId);

    Resources getresourceById(Integer resourceId);
}
