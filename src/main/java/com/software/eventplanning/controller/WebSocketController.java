package com.software.eventplanning.controller;

import com.software.eventplanning.common.Result;
import com.software.eventplanning.mapper.ParticipantsMapper;
import com.software.eventplanning.server.SocketServer;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;

/**
 * websocket
 * 消息推送
 */

@Controller
@RequestMapping("/chatroom")
public class WebSocketController {

    @Autowired
    private SocketServer socketServer;

    @Autowired
    private ParticipantsMapper participantsMapper;

    /**
     * 推送给所有在线用户
     * @return
     */
    @RequestMapping("sendAll")
    @ResponseBody
    public String sendAll(String msg) {
        SocketServer.sendAll(msg);
        return "success";
    }

    /**
     * 推送给同一活动的所有用户
     */
    @RequestMapping("sendActivity")
    @ResponseBody
    public Result sendmsg(@Param(value = "username") String username, @Param(value = "msg") String msg,@Param("activityId") Integer activityId) {
        boolean hasUser = participantsMapper.hasUser(activityId, username);
        if (!hasUser) {
            return Result.error(-1,"当前用户不在活动中");
        }
        List<String> list = participantsMapper.selectUserNameByActivityId(activityId);
        if (list.isEmpty()) {
            return Result.error(-1,"当前活动没有用户");
        }
        SocketServer.SendMany(username, msg, list);
        return Result.success("推送成功", null);
    }

    /**
     * 推送文件给同一活动的所有用户
     */
    @PostMapping("sendFileToActivity")
    @ResponseBody
    public Result sendFileToActivity(@RequestParam("username") String username,
                                     @RequestParam("file") MultipartFile file,
                                     @RequestParam("activityId") Integer activityId) {
        boolean hasUser = participantsMapper.hasUser(activityId, username);
        if (!hasUser) {
            return Result.error(-1, "用户不是该活动参与者");
        }

        List<String> userList = participantsMapper.selectUserNameByActivityId(activityId);
        if (userList.isEmpty()) {
            return Result.error(-1, "活动中没有用户");
        }

        if (file.isEmpty()) {
            return Result.error(-1, "文件是空");
        }

        try {
            ByteBuffer byteBuffer = ByteBuffer.wrap(file.getBytes());
            SocketServer.sendBinaryMessageToMany(byteBuffer, userList);
            return Result.success("文件发送成功");
        } catch (IOException e) {
            return Result.error(-1, "发送文件失败: " + e.getMessage());
        }
    }

    /**
     * 推送给指定用户
     * @return
     */
    @PostMapping("sendPrivateMessage")
    @ResponseBody
    public Result sendPrivateMessage(@RequestParam("fromUsername") String fromUsername,
                                     @RequestParam("toUsername") String toUsername,
                                     @RequestParam("message") String message) {
        SocketServer.sendPrivateMessage(fromUsername, message, toUsername);
        return Result.success("发送成功");
    }

    /**
     * 推送文件给指定用户
     */
    @PostMapping("sendPrivateFile")
    @ResponseBody
    public Result sendPrivateFile(@RequestParam("fromUsername") String fromUsername,
                                  @RequestParam("toUsername") String toUsername,
                                  @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error(-1, "文件是空");
        }

        try {
            ByteBuffer byteBuffer = ByteBuffer.wrap(file.getBytes());
            SocketServer.sendPrivateBinaryMessage(fromUsername, byteBuffer, toUsername);
            return Result.success("文件发送成功");
        } catch (IOException e) {
            return Result.error(-1, "发送文件失败: " + e.getMessage());
        }
}



}
