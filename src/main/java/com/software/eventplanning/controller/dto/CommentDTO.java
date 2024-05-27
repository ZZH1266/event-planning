package com.software.eventplanning.controller.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class CommentDTO {
    private Integer commentId;
    private Integer activityId;
    private String username;
    private String content;
    private Integer parentId;
    private List<CommentDTO> children;
    private Timestamp createdTime;

}
