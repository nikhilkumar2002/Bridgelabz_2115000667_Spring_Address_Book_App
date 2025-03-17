package com.example.SpringAddressBookApp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AuthUserDTO {
    @NotBlank
    @Pattern(regexp = "^[A-Z][a-zA-Z]*$", message = "First letter should be uppercase.")
    private String firstName;

    @NotBlank
    @Pattern(regexp = "^[A-Z][a-zA-Z]*$", message = "First letter should be uppercase.")
    private String lastName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[@#$%^&*()-+=])(?=.*\\d).{8,}$",
            message = "Password must contain 1 uppercase, 1 special character, 1 number, and be at least 8 characters.")
    private String password;
}
