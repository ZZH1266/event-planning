package com.software.eventplanning.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.software.eventplanning.controller.dto.ParticipantsDTO;
import com.software.eventplanning.entity.Participants;

public interface IParticipantService extends IService<Participants> {

    boolean hasUser(ParticipantsDTO participantsDTO);

    Participants convertParticipantsDTOToParticipants(ParticipantsDTO participantsDTO);

}
