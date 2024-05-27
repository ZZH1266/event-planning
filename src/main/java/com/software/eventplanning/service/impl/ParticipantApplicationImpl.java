package com.software.eventplanning.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.software.eventplanning.controller.dto.ParticipantApplicationDTO;
import com.software.eventplanning.entity.ParticipantApplications;
import com.software.eventplanning.entity.Participants;
import com.software.eventplanning.mapper.ParticipantApplicationMapper;
import com.software.eventplanning.mapper.ParticipantsMapper;
import com.software.eventplanning.service.IParticipantApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticipantApplicationImpl extends ServiceImpl<ParticipantApplicationMapper, ParticipantApplications> implements IParticipantApplicationService {

    @Autowired
    private ParticipantsMapper participantsMapper;

    @Override
    public ParticipantApplications apply(Integer activityId, Integer userId, String username) {
        ParticipantApplications participantApplications = new ParticipantApplications();
        participantApplications.setActivityId(activityId);
        participantApplications.setUserId(userId);
        participantApplications.setUsername(username);
        participantApplications.setState("申请中");
        save(participantApplications);
        return participantApplications;
    }

    @Override
    public ParticipantApplications applyApplication(ParticipantApplications participantApplication) {

        // Update the state of the participant application
        participantApplication.setState("已通过");
        updateById(participantApplication);

        // Add the participant to the participants table
        Participants participant = new Participants();
        participant.setActivityId(participantApplication.getActivityId());
        participant.setUserId(participantApplication.getUserId());
        participant.setUsername(participantApplication.getUsername());
        participantsMapper.insert(participant);
        return participantApplication;
    }
}
