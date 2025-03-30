package com.main.fakeMessenger.service;

import com.main.fakeMessenger.pojo.request.auth.RegisterRequest;

public interface AuthService {
    void register(RegisterRequest request);

}
