package com.ms.MicroServiceUser.Response;

import com.ms.MicroServiceUser.Entity.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
}
