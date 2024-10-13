package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entity.RefreshToken;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RefreshTokenRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.utill.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JWTService jwtService;

    public String login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);

        if (!passwordEncoder.matches(password, user.get().getPassword())){
            throw new RuntimeException("Incorrect password");
        }
        Optional<User> userS = userRepository.findByEmail(email);
        User savedUser = userS.get();
        List<?> listRefreshToken = jwtService.generateRefreshToken(savedUser);
                    List<?> listAccessToken = jwtService.generateAccessToken(savedUser);
                    RefreshToken refreshToken = RefreshToken.builder()
                            .token((String) listRefreshToken.get(0))
                            .expireAt((Date) listRefreshToken.get(1))
                            .user(savedUser)
                            .build();
                    RefreshToken token = refreshTokenRepository.save(refreshToken);
                    savedUser.setToken(token);
                    userRepository.save(savedUser);
        String accessToken = jwtService.createAccessToken(userS.get());
        String refreshTokens = jwtService.createRefreshToken(userS.get());

        return "Access-Token :"+accessToken + " " + "Refresh-Token :" + refreshTokens;
    }
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void registerNewUser(SignupRequest signupRequest) {
        Optional<Role> userRole = roleRepository.findByName("USER");

        if (userRepository.existsByEmail(signupRequest.getEmail())){
            throw new RuntimeException("This email already exists");
        }
        User newUser = new User();
        newUser.setFirstName(signupRequest.getFirstName());
        newUser.setLastName(signupRequest.getLastName());
        newUser.setEmail(signupRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        newUser.setRoles(Collections.singleton(userRole.get()));
        newUser.setUpdated_at(new Date());
        newUser.setCreated_at(new Date());
        userRepository.save(newUser);

    }

}
