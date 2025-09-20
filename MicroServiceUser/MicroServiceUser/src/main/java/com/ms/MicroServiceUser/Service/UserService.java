package com.ms.MicroServiceUser.Service;


import com.ms.MicroServiceUser.Exception.MsException;
import com.ms.MicroServiceUser.Request.UserRequest;
import com.ms.MicroServiceUser.Response.AuthResponse;
import com.ms.MicroServiceUser.Response.UserResponse;

public interface UserService {

    UserResponse registerUser(UserRequest request) throws MsException;

    UserResponse updateUser(Long id, UserRequest request) throws MsException;

    void deleteUser(Long id) throws MsException;

    UserResponse getUserByEmail(String email) throws MsException;


    AuthResponse login(String email, String password) throws MsException;


}
