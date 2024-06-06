package com.software.eventplanning.controller.dto;

import lombok.Data;

@Data
public class BackgroundActivitiesApproveBookingsDTO {
    Integer myId;//操作者的ID
    Integer bookingId;//要审批记录的ID
    boolean result;//审批是否同意 true为同意，false为不同意
}
