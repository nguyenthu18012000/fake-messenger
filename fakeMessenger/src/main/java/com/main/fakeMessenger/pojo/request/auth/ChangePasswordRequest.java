package com.main.fakeMessenger.pojo.request.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordRequest {

    @Schema(description = "Username: email or phone number", example = "admin@abc.com | 0123456789")
    String username;

    @Schema(description = "Password", example = "123456")
    String oldPassword;

    @Schema(description = "Password", example = "123456")
    String newPassword;

    @Schema(description = "Password", example = "123456")
    String confirmNewPassword;
}
