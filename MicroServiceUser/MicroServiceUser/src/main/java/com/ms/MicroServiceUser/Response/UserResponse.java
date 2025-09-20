package com.ms.MicroServiceUser.Response;

import com.ms.MicroServiceUser.Entity.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private Roles role;
    private String profileImageUrl;

}

