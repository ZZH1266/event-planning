package com.software.eventplanning.controller.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class NoticesTemplatesDTO {
    String templateName;
    String subject;
    String body;
}
