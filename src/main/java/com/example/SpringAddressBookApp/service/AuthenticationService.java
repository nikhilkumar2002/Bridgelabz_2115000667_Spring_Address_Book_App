package com.example.SpringAddressBookApp.service;


import com.example.SpringAddressBookApp.dto.AuthUserDTO;
import com.example.SpringAddressBookApp.dto.LoginDTO;
import com.example.SpringAddressBookApp.dto.LoginResponseDto;
import com.example.SpringAddressBookApp.model.AuthUser;
import com.example.SpringAddressBookApp.repository.AuthUserRepository;
import com.example.SpringAddressBookApp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private AuthUserRepository authUserRepository;
    @Autowired
    private JwtUtil jwtUtil;


    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String registerUser(AuthUserDTO authUserDTO) {
        if (authUserRepository.existsByEmail(authUserDTO.getEmail())) {
            return "Email is already in use.";
        }

        AuthUser authUser = new AuthUser();
        authUser.setFirstName(authUserDTO.getFirstName());
        authUser.setLastName(authUserDTO.getLastName());
        authUser.setEmail(authUserDTO.getEmail());
        authUser.setPassword(authUserDTO.getPassword());
        authUserRepository.save(authUser);

        return "User registered successfully!";
    }

    public LoginResponseDto loginUser(LoginDTO loginDto) {
        LoginResponseDto response = new LoginResponseDto();
        String token = jwtUtil.generateToken(loginDto.getEmail());

        if (token != null) {
            response.setEmail(loginDto.getEmail());
            response.setMessage("Login successful!");
            response.setToken(token);
        } else {
            response.setMessage("Invalid email or password.");
            response.setToken(null);
        }

        return response;
    }
}
