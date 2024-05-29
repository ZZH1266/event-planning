package com.software.eventplanning.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.software.eventplanning.controller.dto.ReportsDTO;
import com.software.eventplanning.entity.Reports;
import com.software.eventplanning.mapper.ReportsMapper;

public interface IReportsService extends IService<Reports>  {
    Reports generated(ReportsDTO reportsDTO);
}
