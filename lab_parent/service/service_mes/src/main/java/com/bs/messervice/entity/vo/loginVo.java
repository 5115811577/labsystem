package com.bs.messervice.entity.vo;

import lombok.Data;

@Data
public class loginVo {
    private String username;
    private String password;
    private Integer userType;
}
