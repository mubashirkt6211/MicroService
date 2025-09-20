package com.ms.MicroServiceUser.Request;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}