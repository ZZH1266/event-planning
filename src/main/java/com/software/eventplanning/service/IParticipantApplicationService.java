package com.software.eventplanning.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.software.eventplanning.entity.ParticipantApplications;

public interface IParticipantApplicationService extends IService<ParticipantApplications> {
    ParticipantApplications apply(Integer activityId, Integer userId, String username);

    /**
     * 申请通过
     * @return        返回申请的结果
     */
    ParticipantApplications applyApplication(ParticipantApplications participantApplication);
}
