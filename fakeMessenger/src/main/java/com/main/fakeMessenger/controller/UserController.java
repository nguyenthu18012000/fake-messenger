package com.main.fakeMessenger.controller;

import com.main.fakeMessenger.base.ApiResponse;
import com.main.fakeMessenger.pojo.request.user.UpdateRequest;
import com.main.fakeMessenger.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/update")
    public ResponseEntity<ApiResponse<UpdateRequest>> update( @RequestBody UpdateRequest request) {
        userService.update(request);
        return ResponseEntity.ok(new ApiResponse<>("200", "success", request));
    }

}
