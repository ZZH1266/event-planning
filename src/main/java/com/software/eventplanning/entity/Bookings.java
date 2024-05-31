package com.software.eventplanning.entity;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Data
@TableName("resource_bookings")
public class Bookings {
    @TableId(value = "booking_id", type = IdType.AUTO)
    Integer bookingId;
    @TableField(value = "resource_id")
    Integer resourceId;
    @TableField(value = "resource_name")
    String resourceName;
    @TableField(value = "activity_id")
    Integer activityId;
    @TableField(value="booked_by")
    Integer userId;
    @TableField(value = "start_time")
    DateTime startTime;
    @TableField(value = "end_time")
    DateTime endTime;
    @TableField(value="status")
    String status;
    @TableField(value="created_at")
    Timestamp createdTime;

}
