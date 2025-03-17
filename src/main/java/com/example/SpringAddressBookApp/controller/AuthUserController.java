package com.example.SpringAddressBookApp.controller;


import com.example.SpringAddressBookApp.dto.AuthUserDTO;
import com.example.SpringAddressBookApp.dto.LoginDTO;
import com.example.SpringAddressBookApp.dto.LoginResponseDto;
import com.example.SpringAddressBookApp.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthUserController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody AuthUserDTO authUserDTO) {
        String response = authenticationService.registerUser(authUserDTO);
        if (response.equals("User registered successfully!")) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDto) {
        LoginResponseDto response = authenticationService.loginUser(loginDto);
        if (response.getToken() != null) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("{\"message\": \"Invalid email or password!\"}");
    }

}
