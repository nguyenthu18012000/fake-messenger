package com.main.fakeMessenger.pojo.request.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequest {

    @Schema(description = "Username: email or phone number", example = "admin@abc.com | 0123456789")
    private String username;

    @Schema(description = "Password", example = "123456")
    private String password;

}
