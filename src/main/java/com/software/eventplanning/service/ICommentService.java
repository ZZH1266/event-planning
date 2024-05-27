package com.software.eventplanning.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.software.eventplanning.controller.dto.CommentDTO;
import com.software.eventplanning.entity.Comments;

import java.util.List;

public interface ICommentService extends IService<Comments> {
    int addComment(Comments comment);

    void setChildren(CommentDTO commentDTO);

    List<CommentDTO> getAllComments(Integer activityId);
}
