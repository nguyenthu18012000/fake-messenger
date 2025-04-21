package com.main.fakeMessenger.service;

import com.main.fakeMessenger.pojo.request.auth.ChangePasswordRequest;
import com.main.fakeMessenger.pojo.request.auth.RegisterRequest;
import com.main.fakeMessenger.pojo.request.auth.UserLoginRequest;
import com.main.fakeMessenger.pojo.response.auth.UserLoginResponse;
import jakarta.validation.Valid;

public interface AuthService {
    void register(RegisterRequest request);

    UserLoginResponse login(UserLoginRequest request);

    void changePassword(ChangePasswordRequest request);
}
