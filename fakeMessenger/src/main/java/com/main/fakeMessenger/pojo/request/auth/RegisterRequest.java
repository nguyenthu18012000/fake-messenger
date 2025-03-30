package com.main.fakeMessenger.pojo.request.auth;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotNull(message = "UserName isn't null")
    private String username;

    @NotNull(message = "Password isn't null")
    private String password;

    private String name;

}
