package com.main.fakeMessenger.service;

import com.main.fakeMessenger.pojo.request.auth.RegisterRequest;
import com.main.fakeMessenger.pojo.request.user.UpdateRequest;

public interface UserService {
    void register(RegisterRequest request);


    void update(UpdateRequest request);
}
