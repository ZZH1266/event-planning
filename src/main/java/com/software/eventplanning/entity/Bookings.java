package com.software.eventplanning.entity;

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
@Setter
@Getter
@TableName("resource_bookings")
public class Bookings {
    @TableId(value = "booking_id", type = IdType.AUTO)
    Integer bookingId;
    Integer resourceId;
    Integer activityId;
    @TableField(value="booked_by")
    Integer userId;
    Timestamp startTime;
    Timestamp endTime;
    @TableField(value="created_at")
    Timestamp createdTime;

}
