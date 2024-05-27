package com.software.eventplanning.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.software.eventplanning.controller.dto.CommentDTO;
import com.software.eventplanning.entity.Comments;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CommentMapper extends BaseMapper<Comments> {

    @Select("select comment_id as commentId, activity_id as activityId, username, content, parent_id as parentId, created_at as createdTime from comments where activity_id = #{activityId} and parent_id = #{parentId}")
    List<CommentDTO> getCommentsByActivityIdAndParentId(@Param(value = "activityId") Integer activityId,@Param(value = "parentId") Integer parentId);
}
