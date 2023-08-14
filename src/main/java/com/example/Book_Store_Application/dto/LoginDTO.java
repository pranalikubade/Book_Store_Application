package com.example.Book_Store_Application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LoginDTO {
    @NotBlank(message = "UserName Cannot be empty")
    @Pattern(regexp = "^[a-zA-Z_0-9]{1,}[!@#$%^&*][a-zA-Z_0-9]{1,}$", message = "Password must have at least one Special Character")
    public String password;

    @NotNull(message = "Email ID Cannot be empty")
    @Pattern(regexp = "^[a-z_0-9]{2,}@gmail.com$", message = "give email in format :: xxxxxxxx@gmail.com")
    public String email;
}
