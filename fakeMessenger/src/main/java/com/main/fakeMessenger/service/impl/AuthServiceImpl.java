package com.main.fakeMessenger.service.impl;

import com.main.fakeMessenger.pojo.request.auth.RegisterRequest;
import com.main.fakeMessenger.pojo.request.auth.UserLoginRequest;
import com.main.fakeMessenger.pojo.response.auth.UserLoginResponse;
import com.main.fakeMessenger.service.AuthService;
import com.main.fakeMessenger.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    @Override
    public void register(RegisterRequest request) {
        userService.register(request);
    }

    @Override
    public UserLoginResponse login(UserLoginRequest request) {
        return userService.login(request);
    }
}
