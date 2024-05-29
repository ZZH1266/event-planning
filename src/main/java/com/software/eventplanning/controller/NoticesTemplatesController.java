package com.software.eventplanning.controller;

import com.software.eventplanning.common.Constants;
import com.software.eventplanning.common.Result;
import com.software.eventplanning.controller.dto.NoticesTemplatesDTO;
import com.software.eventplanning.controller.dto.ResourcesDTO;
import com.software.eventplanning.mapper.NoticesTemplatesMapper;
import com.software.eventplanning.service.INoticesTemplatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/noticestemplates")
public class NoticesTemplatesController {

    @Autowired
    private INoticesTemplatesService nt;

    @PostMapping("/add")
    @ResponseBody
    public Result addnoticestemplate(@RequestBody NoticesTemplatesDTO noticesTemplatesDTO) {
        String templatename =noticesTemplatesDTO.getTemplateName();
        String subject=noticesTemplatesDTO.getSubject();
        String body=noticesTemplatesDTO.getBody();
        if(templatename==null){
            return Result.error(Constants.CODE_400,"模板名称不能为空");
        }
        if(subject==null){
            return Result.error(Constants.CODE_400,"模板主题不能为空");
        }
        if(body==null){
            return Result.error(Constants.CODE_400,"模板结构不能为空");
        }
        return Result.success(nt.add(noticesTemplatesDTO));
    }
}
