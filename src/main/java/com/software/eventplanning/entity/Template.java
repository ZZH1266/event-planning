package com.software.eventplanning.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

//活动模板当前分为两类：户外运动和室内活动 所有类型的模板的属性都放在当前一个实体类之中，对于属于不同类型模板的活动，填写共同字段以及该类型对应字段即可。
@Data
@TableName("activity_templates")
public class Template {
    //下面为所有活动模板共有内容
    @TableId(value = "activity_id",type = IdType.AUTO)
    Integer activityId; //活动ID对应活动表，主键
    @TableField(value = "template_id")
    Integer templateID; //模板ID
    @TableField(value="activity_size")
    Integer activitySize; //活动规模（多少人）
    @TableField(value = "updated_at")
    Timestamp updatedAt;//该活动对应的模板信息的最后更改时间 该字段不需要写入，Timestamp类型字段在插入或更新记录时自动更新
    @TableField(value = "place_plan_to_use")
    String placePlanToUse; //预计使用的场馆类型

    //以下为户外运动活动模板独有内容
    @TableField(value = "sports_kind")
    String sportsKind;//户外运动的类型
    @TableField(value = "safety_officer_name")
    String safetyOfficerName; //安全员姓名

    //以下为室内运动活动模板独有内容
    @TableField(value = "indoor_kind")
    String indoorKind;//室内运动类型
    @TableField(value = "host_name")
    String hostName;//主持人姓名
    @TableField(value="teacher_name")
    String teacherName;//授课教师姓名

}
