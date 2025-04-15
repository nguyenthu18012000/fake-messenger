package com.main.fakeMessenger.service.impl;

import com.main.fakeMessenger.base.exception.CustomException;
import com.main.fakeMessenger.config.JwtUtil;
import com.main.fakeMessenger.constant.ErrorConstant;
import com.main.fakeMessenger.constant.RegexConstant;
import com.main.fakeMessenger.pojo.entity.User;
import com.main.fakeMessenger.pojo.request.auth.RegisterRequest;
import com.main.fakeMessenger.pojo.request.auth.UserLoginRequest;
import com.main.fakeMessenger.pojo.response.auth.UserLoginResponse;
import com.main.fakeMessenger.repository.UserRepository;
import com.main.fakeMessenger.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public void register(RegisterRequest request) {

        if (isInValidUsername(request.getUsername())) {
            throw new CustomException(ErrorConstant.INVALID_USERNAME_FORMAT);
        }

        if (isEmail(request.getUsername())) {
            if (userRepository.findByEmail(request.getUsername()).isPresent()) {
                throw new CustomException(ErrorConstant.EXISTED_USERNAME);
            }
        } else {
            if (userRepository.findByPhone(request.getUsername()).isPresent()) {
                throw new CustomException(ErrorConstant.EXISTED_USERNAME);
            }
        }

        User user = new User();
        user.setUsername(request.getUsername());

        if (isEmail(request.getUsername())) {
            user.setEmail(request.getUsername());
        } else {
            user.setPhone(request.getUsername());
        }

        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());
        Date now = new Date();
        user.setCreated_at(now);
        userRepository.save(user);
    }

    @Override
    public UserLoginResponse login(UserLoginRequest request) {
        if (isInValidUsername(request.getUsername())) {
            throw new CustomException(ErrorConstant.INVALID_USERNAME_FORMAT);
        }

        User user = null;
        if (isEmail(request.getUsername())) {
            Optional<User> existedUser = userRepository.findByEmail(request.getUsername());
            if (existedUser.isPresent()) {
                user = existedUser.get();
            }
        } else {
            Optional<User> existedUser = userRepository.findByPhone(request.getUsername());
            if (existedUser.isPresent()) {
                user = existedUser.get();
            }
        }
        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorConstant.INVALID_USERNAME_OR_PASSWORD);
        }

        UserLoginResponse response = new UserLoginResponse();

        response.setAccessToken(jwtUtil.generateLoginJwtToken(user));

        return response;
    }

    public boolean isInValidUsername(String username) {
        return !isPhone(username) && !isEmail(username);
    }

    public boolean isEmail(String request) {
        return Pattern.compile(RegexConstant.EMAIL_REGEX).matcher(request).matches();
    }

    public boolean isPhone(String request) {
        return Pattern.compile(RegexConstant.PHONE_REGEX).matcher(request).matches();
    }
}
