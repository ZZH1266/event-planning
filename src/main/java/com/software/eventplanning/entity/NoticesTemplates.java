package com.software.eventplanning.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.security.Timestamp;

@Data
@Getter
@Setter
@TableName("notification_templates")
public class NoticesTemplates {
    @TableId(value = "template_id", type = IdType.AUTO)
    Integer templateId;
    String templateName;
    String subject;
    String body;
    @TableField(value="created_at")
    Timestamp CreateTime;
    @TableField(value="updated_at")
    Timestamp upaateTime;
}
