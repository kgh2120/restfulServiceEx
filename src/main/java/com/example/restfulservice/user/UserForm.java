package com.example.restfulservice.user;

import lombok.Data;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class UserForm {

    @Size(min=2, message = "Name은 2글자 이상 입력해주세요")
    private String name;
    private String password;
    private String ssn;

    public User createUser() {
        return new User(null,name, LocalDateTime.now(),password,ssn);
    }
}
