package com.main.fakeMessenger.service;

import com.main.fakeMessenger.pojo.request.auth.RegisterRequest;

public interface UserService {
    void register(RegisterRequest request);


}
