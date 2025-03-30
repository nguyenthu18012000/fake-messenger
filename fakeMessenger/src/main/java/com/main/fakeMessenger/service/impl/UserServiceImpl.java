package com.main.fakeMessenger.service.impl;

import com.main.fakeMessenger.base.exception.CustomException;
import com.main.fakeMessenger.constant.RegexConstant;
import com.main.fakeMessenger.pojo.entity.User;
import com.main.fakeMessenger.pojo.request.auth.RegisterRequest;
import com.main.fakeMessenger.pojo.request.user.UpdateRequest;
import com.main.fakeMessenger.repository.UserRepository;
import com.main.fakeMessenger.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void register(RegisterRequest request) {

        if(!isValidUserName(request))
            throw new CustomException("001");

        if(isEmail(request)) {
            if(userRepository.findByEmail(request.getUsername()).isPresent()) {
                throw new CustomException("002");
            }
        } else {
            if(userRepository.findByPhone(request.getUsername()).isPresent()) {
                throw new CustomException("002");
            }
        }

        User user = new User();

        if(isEmail(request)) {
            user.setEmail(request.getUsername());
        } else {
            user.setPhone(request.getUsername());
        }

        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());
        userRepository.save(user);
    }


    @Override
    public void update(UpdateRequest request) {

        userRepository.findById(request.getId()).ifPresent(user -> {
            user.setName(request.getName());
            user.setImage_url(request.getImage_url());
            userRepository.save(user);
        });

    }

    public boolean isValidUserName(RegisterRequest request) {
        return Pattern.compile(RegexConstant.PHONE_REGEX).matcher(request.getUsername()).matches() ||
                Pattern.compile(RegexConstant.EMAIL_REGEX).matcher(request.getUsername()).matches();
    }

    public boolean isEmail(RegisterRequest request) {
        return Pattern.compile(RegexConstant.EMAIL_REGEX).matcher(request.getUsername()).matches();
    }
}
