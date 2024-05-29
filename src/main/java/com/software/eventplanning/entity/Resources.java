package com.software.eventplanning.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.security.Timestamp;

@Data
@Getter
@Setter
public class Resources {
    @TableId(value = "resource_id", type = IdType.AUTO)
    Integer resourceId;
    String resourceName;
    String resourceType;
    String description;
    String status;
    @TableField(value="created_at")
    Timestamp createTime;
    @TableField(value="updated_at")
    Timestamp updateTime;
}
