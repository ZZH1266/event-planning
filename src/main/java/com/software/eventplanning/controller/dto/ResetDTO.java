package com.software.eventplanning.controller.dto;

import lombok.Data;

@Data
public class ResetDTO {
    private String username;
    private String password;
    private String newPassword;
    private String email;
    private String code;
}
