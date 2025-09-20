package com.ms.MS_User.Service;

import com.ms.MS_User.Entity.User;
import com.ms.MS_User.Exception.MsException;
import com.ms.MS_User.Repository.UserRepository;
import com.ms.MS_User.Request.UserRequest;
import com.ms.MS_User.Response.AuthResponse;
import com.ms.MS_User.Response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserResponse registerUser(UserRequest request) throws MsException {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new MsException("Email already exists: " + request.getEmail());
        }
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        User savedUser = userRepository.save(user);
        return new UserResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getRole(),
                savedUser.getProfileImageUrl()
        );
    }


    @Override
    public UserResponse updateUser(Long id, UserRequest request) throws MsException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new MsException("User not found with ID: " + id));

        if (request.getEmail() != null && !request.getEmail().isBlank()) {
            userRepository.findByEmail(request.getEmail())
                    .ifPresent(existingUser -> {
                        if (!existingUser.getId().equals(id)) {
                            throw new RuntimeException("Email already exists: " + request.getEmail());
                        }
                    });
            user.setEmail(request.getEmail());
        }
        if (request.getName() != null && !request.getName().isBlank()) {
            user.setName(request.getName());
        }
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        if (request.getRole() != null) {
            user.setRole(request.getRole());
        }
        if (request.getProfileImageUrl() != null && !request.getProfileImageUrl().isBlank()) {
            user.setProfileImageUrl(request.getProfileImageUrl());
        }
        User updatedUser = userRepository.save(user);
        return new UserResponse(
                updatedUser.getId(),
                updatedUser.getName(),
                updatedUser.getEmail(),
                updatedUser.getRole(),
                updatedUser.getProfileImageUrl()
        );
    }


    @Override
    public void deleteUser(Long id) throws MsException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new MsException("User not found with ID: " + id));

        userRepository.delete(user);
    }

    @Override
    public UserResponse getUserById(Long id) throws MsException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new MsException("User not found with ID: " + id));
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getProfileImageUrl()
        );
    }


    @Override
    public AuthResponse login(String email, String password) throws MsException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new MsException("Invalid Email"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new MsException("Invalid Password");
        }

        // Generate JWT
//        String token = jwtService.generateToken(user);

        return new AuthResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
//                token
        );
    }

}
