package com.example.restfulservice.user;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserForm {

    private String name;

    public User createUser() {
        return new User(null,name, LocalDateTime.now());
    }
}
