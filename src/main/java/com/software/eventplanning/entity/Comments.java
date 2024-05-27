package com.software.eventplanning.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class Comments {
    @TableId(type = IdType.AUTO)
    private Integer commentId;
    private Integer activityId;
    private Integer userId;
    private String username;
    private String content;
    private Integer parentId;
    private Timestamp createdAt;
}
