package com.ms.MicroServiceUser.Controller;

import com.ms.MicroServiceUser.Exception.MsException;
import com.ms.MicroServiceUser.Request.LoginRequest;
import com.ms.MicroServiceUser.Request.UserRequest;
import com.ms.MicroServiceUser.Response.AuthResponse;
import com.ms.MicroServiceUser.Response.UserResponse;
import com.ms.MicroServiceUser.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Validated
@CrossOrigin
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest request) throws MsException {
        UserResponse response = userService.registerUser(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) throws MsException {
        AuthResponse response = userService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) throws MsException {
        String email = userDetails.getUsername();
        UserResponse user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }

}
