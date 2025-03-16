package com.main.fakeMessenger.controller;

import com.main.fakeMessenger.base.ApiResponse;
import com.main.fakeMessenger.pojo.request.auth.RegisterRequest;
import com.main.fakeMessenger.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegisterRequest>> registerUser(@Valid @RequestBody RegisterRequest request) {
        authService.register(request);

        return ResponseEntity.ok(new ApiResponse<>("200", "Register successfully", null));
    }


}
