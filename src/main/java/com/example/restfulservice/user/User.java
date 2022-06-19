package com.example.restfulservice.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonFilter("UserInfo")
public class User {
    private Integer id;
    private String name;
    private LocalDateTime joinDate;

    private String password;
    private String ssn;

}
