package com.software.eventplanning.entity;

import lombok.Data;


public enum Status{

    Approved("已批准"),
    Denied("已拒绝"),
    Wating("待审核");

    private String status_;

    Status(String status) {
        this.status_ = status;
    }

    public String getStatus() {
        return status_;
    }
}
