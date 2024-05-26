package com.software.eventplanning.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.websocket.Session;
import java.io.Serializable;

/**
 * 聊天室类
 */
@Data
@AllArgsConstructor
public class Client implements Serializable {
    private static final long serialVersionUID = 8957107006902627635L;
    private Integer activityId;
    private String userName;
    private Session session;

    public Client(String userName, Session session) {
        this.userName = userName;
        this.session = session;
    }
}
