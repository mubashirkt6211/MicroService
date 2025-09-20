package com.ms.MicroServiceUser.Request;

import com.ms.MicroServiceUser.Entity.Roles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-={}|\\[\\]\\\\:\";'<>?,./]).{8,}$",
            message = "Password must be at least 8 characters long, contain at least one uppercase letter, and one special character"
    )    private String password;

    private Roles role;

    private String profileImageUrl;

}
