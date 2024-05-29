package com.software.eventplanning.controller;

import cn.hutool.core.util.StrUtil;
import com.software.eventplanning.common.Constants;
import com.software.eventplanning.common.Result;
import com.software.eventplanning.controller.dto.ResourcesDTO;
import com.software.eventplanning.entity.Resources;
import com.software.eventplanning.mapper.ResourcesMapper;
import com.software.eventplanning.service.IResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resources")
public class ResourcesController {
    @Autowired
    private IResourcesService resourcesService;

    @PostMapping("/add")
    @ResponseBody
    public Result addResource(@RequestBody ResourcesDTO resourcesDTO) {
        String resourcename = resourcesDTO.getResourceName();
        String resourcetype = resourcesDTO.getResourceType();
        if(resourcename==null){
            return Result.error(Constants.CODE_400,"资源名称不能为空");
        }
        if(resourcetype!=null||resourcetype.equals("场地")||resourcetype.equals("设备")){
        return Result.success(resourcesService.add(resourcesDTO));
        }
        else return Result.error(Constants.CODE_400,"资源类型为空或者不对");
    }

    @PostMapping("/update")
    @ResponseBody
    public Result updateResource(@RequestBody ResourcesDTO resourcesDTO,@RequestParam Integer resourceId) {
        String resourcename = resourcesDTO.getResourceName();
        String resourcetype = resourcesDTO.getResourceType();
        if(resourcename==null){
            return Result.error(Constants.CODE_400,"资源名称不能为空");
        }
        if(resourcetype!=null||resourcetype.equals("场地")||resourcetype.equals("设备")){
            return Result.success(resourcesService.update(resourcesDTO,resourceId));
        }
        else return Result.error(Constants.CODE_400,"资源类型为空或者不对");
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public Result deleteResource(@RequestParam Integer resourceId) {
        if(resourceId==null){
            return Result.error(Constants.CODE_400,"请选择需要删除的资源");
        }
        if(resourcesService.delete(resourceId)){
        return Result.success("删除资源成功",null);
    }
        else return Result.error(400,"删除资源失败");
    }

    @GetMapping("/getdetail")
    @ResponseBody
    public Result getResourceDetail(@RequestParam Integer resourceId) {
        Resources resources=resourcesService.getresourceById(resourceId);
        if(resources==null){
            return Result.error(400,"查找的资源不存在");
        }
        else return Result.success(resources);
    }

}
