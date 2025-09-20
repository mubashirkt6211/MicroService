package com.ms.MS_User.Response;

import com.ms.MS_User.Entity.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private Long id;
    private String name;
    private String email;
    private Roles role;
    private String token;
}
