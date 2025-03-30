package com.main.fakeMessenger.pojo.response.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginResponse {
    @Schema(description = "Access token", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYyNjMwNjIwMCwiaWF0IjoxNjI2MzA1NDAwfQ.7")
    private String accessToken;

    @Schema(description = "Refresh token", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYyNjMwNjIwMCwiaWF0IjoxNjI2MzA1NDAwfQ.7")
    private String refreshToken;
}
