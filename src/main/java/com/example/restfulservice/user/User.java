package com.example.restfulservice.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonFilter("UserInfo")
@ApiModel(description = "사용자 상세 정보를 위한 도메인 객체") // Swagger에서 설명을 위한 annotation
@Entity
public class User {
    @Id @GeneratedValue
    private Integer id;

    @ApiModelProperty(notes = "사용자 이름을 입력해 주세요.")
    private String name;
    
    private LocalDateTime joinDate;

    @ApiModelProperty(notes = "사용자 비밀번호을 입력해 주세요.")
    private String password;

    @ApiModelProperty(notes = "사용자 주민등록번호을 입력해 주세요.")
    private String ssn;

}
