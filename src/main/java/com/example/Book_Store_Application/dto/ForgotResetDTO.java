package com.example.Book_Store_Application.dto;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class ForgotResetDTO {
   @NotBlank(message = "Password Cannot be empty")
    @Pattern(regexp = "^[a-zA-Z_0-9]{1,}[!@#$%^&*][a-zA-Z_0-9]{1,}$", message = "password must have 1 Special Character")
    public String confirmPassword;

    @NotBlank(message = "Password Cannot be empty")
    @Pattern(regexp = "^[a-zA-Z_0-9]{1,}[!@#$%^&*][a-zA-Z_0-9]{1,}$", message = "password must have 1 Special Character")
    public String password;

    @NotNull(message = "Token Cannot be empty")
    public String token;


}

