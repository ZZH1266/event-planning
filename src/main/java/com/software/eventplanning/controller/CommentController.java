package com.software.eventplanning.controller;


import com.software.eventplanning.common.Result;
import com.software.eventplanning.controller.dto.CommentDTO;
import com.software.eventplanning.entity.Comments;
import com.software.eventplanning.service.ICommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/activity/comment")
public class CommentController {

    @Resource
    private ICommentService commentService;

    @GetMapping("/{activityId}")
    public Result getAllComments(@PathVariable Integer activityId) {
        return Result.success(commentService.getAllComments(activityId));
    }

    @PostMapping("/")
    public Result addComment(@RequestBody Comments comments) {
        return Result.success(commentService.addComment(comments));
    }


}
