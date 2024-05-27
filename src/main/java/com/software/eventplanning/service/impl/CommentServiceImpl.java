package com.software.eventplanning.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.software.eventplanning.controller.dto.CommentDTO;
import com.software.eventplanning.entity.Comments;
import com.software.eventplanning.mapper.CommentMapper;
import com.software.eventplanning.service.ICommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comments> implements ICommentService {

    @Resource
    private CommentMapper commentMapper;

    @Override
    public int addComment(Comments comment) {
        return commentMapper.insert(comment);
    }

    @Override
    public List<CommentDTO> getAllComments(Integer activityId) {
        List<CommentDTO> comments = commentMapper.getCommentsByActivityIdAndParentId(activityId, 0);
        comments.forEach(this::setChildren);
        return comments;
    }

    /**
     * 递归获取
     * @param commentDTO 参数
     */
    @Override
    public void setChildren(CommentDTO commentDTO) {
        List<CommentDTO> children = commentMapper.getCommentsByActivityIdAndParentId(commentDTO.getActivityId(), commentDTO.getCommentId());
        if (!children.isEmpty()) {
            commentDTO.setChildren(children);
            children.forEach(this::setChildren);
        }
    }

}
