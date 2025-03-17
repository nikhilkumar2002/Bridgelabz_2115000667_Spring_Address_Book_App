package com.example.SpringAddressBookApp.dto;

import lombok.Setter;

public class LoginResponseDto {
    @Setter
    private String email;
    @Setter
    private String message;
    @Setter
    private String token;

    // Getters and Setters
    public String getMessage() { return message; }

    public String getToken() { return token; }

    public String getEmail(){return email;}
}
