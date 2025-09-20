package com.ms.MS_User.Service;

import com.ms.MS_User.Exception.MsException;
import com.ms.MS_User.Request.UserRequest;
import com.ms.MS_User.Response.AuthResponse;
import com.ms.MS_User.Response.UserResponse;

public interface UserService {

    UserResponse registerUser(UserRequest request) throws MsException;

    UserResponse updateUser(Long id, UserRequest request) throws MsException;

    void deleteUser(Long id) throws MsException;

    UserResponse getUserById(Long id) throws MsException;

    AuthResponse login(String email, String password) throws MsException;


}
