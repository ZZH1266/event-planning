package com.software.eventplanning.controller;

import com.software.eventplanning.common.Result;
import com.software.eventplanning.mapper.ParticipantsMapper;
import com.software.eventplanning.server.SocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public Result sendmsg(String msg, Integer activityId) {
        List<String> list = participantsMapper.selectUserNameByActivityId(activityId);
        SocketServer.SendMany(msg, list);
        return Result.success();
    }

}
