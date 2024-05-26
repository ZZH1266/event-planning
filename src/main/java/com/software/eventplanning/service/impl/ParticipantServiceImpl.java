package com.software.eventplanning.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.software.eventplanning.controller.dto.ParticipantsDTO;
import com.software.eventplanning.entity.Participants;
import com.software.eventplanning.entity.Users;
import com.software.eventplanning.mapper.ParticipantsMapper;
import com.software.eventplanning.service.ILoginService;
import com.software.eventplanning.service.IParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticipantServiceImpl extends ServiceImpl<ParticipantsMapper, Participants> implements IParticipantService {

    @Autowired
    private ILoginService userService;

    /**
     * 判断在参与者表添加的用户在用户表中是否存在
     *
     * @param participantsDTO 参与者的信息
     * @return 返回是否存在
     */
    @Override
    public boolean hasUser(ParticipantsDTO participantsDTO) {
        QueryWrapper<Users> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", participantsDTO.getUserId());
        wrapper.eq("username", participantsDTO.getUsername());
        Users one = userService.getOne(wrapper);
        return one != null;
    }


    /**
     * 将ParticipantsDTO转换为Participants
     *
     * @param participantsDTO 参与者的信息
     * @return 返回转换后的Participants
     */
    @Override
    public Participants convertParticipantsDTOToParticipants(ParticipantsDTO participantsDTO) {
        // 查找participants表中是否已存在该用户
        QueryWrapper<Participants> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", participantsDTO.getUserId());
        wrapper.eq("username", participantsDTO.getUsername());
        Participants one = getOne(wrapper);
        if (one != null) {
            return one;
        } else {
            Participants participants = new Participants();
            participants.setUserId(participantsDTO.getUserId());
            participants.setActivityId(participantsDTO.getActivityId());
            participants.setActivityRole(participantsDTO.getActivityRole());
            participants.setUsername(participantsDTO.getUsername());
            return participants;
        }
    }
}
