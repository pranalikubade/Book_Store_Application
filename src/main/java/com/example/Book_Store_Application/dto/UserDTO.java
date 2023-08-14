package com.example.Book_Store_Application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;
@Data
public class UserDTO {
    @NotBlank(message = "FirstName Cannot be empty")
    @Pattern(regexp = "^[A-Z]{1}[a-z]{1,}$", message = "FirstName must start with capital")
    public String firstName;

    @NotBlank(message = "LastName Cannot be empty")
    @Pattern(regexp = "^[A-Z]{1}[a-z]{1,}$", message = "LastName must start with capital")
    public String lastName;

    @NotBlank(message = "UserName Cannot be empty")
    @Pattern(regexp = "^[a-zA-Z_0-9]{1,}[!@#$%^&*][a-zA-Z_0-9]{1,}$", message = "Password must have at least Special Character")
    public String password;

    @NotNull(message = "Email ID Cannot be empty")
    @Pattern(regexp = "^[a-z_0-9]{2,}@gmail.com$", message = "give email in format :: xxxxxxxx@gmail.com")
    public String email;

    @JsonFormat(pattern = "dd MM yyyy")
    @NotNull(message = "Date of Birth  should not be empty")
    @PastOrPresent(message = "dob should be past or today's date")
    public LocalDate dob;

    public  LocalDate updatedDate;
    public LocalDate registeredDate;

}
