package com.software.eventplanning.entity;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@TableName("resource_allocations")
public class Allocations {
    @TableId(value="allocation_id",type = IdType.AUTO)
    Integer allocationId;
    @TableField(value="resource_id")
    Integer resourceId;
    @TableField(value="resource_name")
    String resourceName;
    @TableField(value="start_time")
    String  startTime;
    @TableField(value="end_time")
    String endTime;
    @TableField(value="allocated_at")
    Timestamp allocatedAt;
}
