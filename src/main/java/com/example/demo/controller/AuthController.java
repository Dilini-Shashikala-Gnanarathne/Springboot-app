package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {
        @Autowired
        AuthService authService;

        @PostMapping("/register")
        public ResponseEntity<String> UserRegister(@RequestBody SignupRequest signupRequest) {
            authService.registerNewUser(signupRequest);
            return ResponseEntity.ok("User registered successfully");
        }

        @PostMapping("/login")
        public ResponseEntity<String> UserLogin(@RequestBody LoginRequest loginRequest) {
            String tokens = authService.login(loginRequest.getEmail(), loginRequest.getPassword());
            return ResponseEntity.ok(tokens);
        }
    }
