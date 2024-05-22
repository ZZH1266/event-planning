package com.software.eventplanning.entity;

import lombok.Data;


public enum ActivityRole {
    Guest("嘉宾"),
    Speaker("演讲者"),
    Worker("工作人员"),
    Participant("参与者");

    private String role;

    ActivityRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
