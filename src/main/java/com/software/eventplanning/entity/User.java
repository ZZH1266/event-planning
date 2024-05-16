package com.software.eventplanning.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
@TableName
public class User {
    Integer id;
    String username;
    @JsonIgnore
    String password;
    String email;
    String role;
}
