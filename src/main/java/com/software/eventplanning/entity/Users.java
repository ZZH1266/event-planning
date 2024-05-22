package com.software.eventplanning.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@Getter
@Setter
@ToString
@TableName
public class Users {
    @TableId(value = "user_id", type = IdType.AUTO)
    Integer id;
    @TableField(value = "username")
    String username;
    @JsonIgnore
    String password;
    String email;
    @TableField(value = "created_at")
    Timestamp createdTime;
    @TableField(value = "updated_at")
    Timestamp updatedTime;
    Integer role;
}
