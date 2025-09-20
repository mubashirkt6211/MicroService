package com.ms.MS_User.Response;

import com.ms.MS_User.Entity.Roles;
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

