package com.software.eventplanning.controller;

import com.software.eventplanning.common.Result;
import com.software.eventplanning.controller.dto.ReportsDTO;
import com.software.eventplanning.entity.Reports;
import com.software.eventplanning.service.IReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/reports")
public class ReportsController {
    @Resource
    private IReportsService reportsService;

    @PostMapping("/generate")
    @ResponseBody
    public Result r(@RequestBody ReportsDTO reportsDTO) {

        return Result.success(reportsService.generated(reportsDTO));
    }
}
