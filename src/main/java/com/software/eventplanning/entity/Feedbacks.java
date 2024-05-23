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
@Getter
@Setter
@TableName
public class Feedbacks {
   @TableId(value = "feedback_id",type=IdType.AUTO)
    private  Integer feedbackId;
    private  Integer userId;
    private  Integer activityId;
    private  Integer rating;
    @TableField(value = "comments")
    private  String context;
    @TableField(value="submitted_at",select=false)
    private Timestamp submittedTime;
}
