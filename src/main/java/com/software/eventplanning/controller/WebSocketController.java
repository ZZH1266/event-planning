package com.software.eventplanning.controller;

import com.software.eventplanning.common.Result;
import com.software.eventplanning.mapper.ParticipantsMapper;
import com.software.eventplanning.server.SocketServer;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * websocket
 * 消息推送
 */

@Controller
public class WebSocketController {

    @Autowired
    private SocketServer socketServer;

    @Autowired
    private ParticipantsMapper participantsMapper;

    /**
     * 客户端页面
     * @return
     */
    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

    /**
     * 服务端页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/admin")
    public String admin(Model model) {
        int num = socketServer.getOnlineNum();
        List<String> list = socketServer.getOnlineUsers();

        model.addAttribute("num", num);
        model.addAttribute("users", list);
        return "admin";
    }

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

}
