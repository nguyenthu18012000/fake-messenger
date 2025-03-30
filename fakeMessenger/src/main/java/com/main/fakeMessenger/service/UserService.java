package com.main.fakeMessenger.service;

import com.main.fakeMessenger.pojo.request.auth.RegisterRequest;
import com.main.fakeMessenger.pojo.request.auth.UserLoginRequest;
import com.main.fakeMessenger.pojo.response.auth.UserLoginResponse;

public interface UserService {
    void register(RegisterRequest request);

    UserLoginResponse login(UserLoginRequest request);
}
