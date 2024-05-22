package com.software.eventplanning.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class Events {
    @TableId(value = "event_ID", type = IdType.AUTO)
    private Integer eventID;
    private String eventName;
    private String eventAddress;
    private String eventDetail;
    private Timestamp beginTime;
    private Timestamp endTime;
    private Integer eventModel;
    private Integer eventStatus;
}
