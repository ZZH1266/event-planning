package com.software.eventplanning.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.security.Timestamp;

@Data
@Setter
@Getter
@TableName("activity_reports")
public class Reports {
    @TableId(value = "report_id", type = IdType.AUTO)
    Integer reportId;
    @TableField(value="activity_id")
    Integer activityId;
    @TableField(value="generated_by")
    Integer userId;
    @JsonIgnore
    @TableField(value="generated_at",select = false)
    Timestamp createTime;
    @TableField(value="participant_count")
    Integer participantCount;
    @TableField(value="overall_effectiveness")
    String overallEffectiveness;
    @TableField(value="report_content")
    String  reportContent;
}
