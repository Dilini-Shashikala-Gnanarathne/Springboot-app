package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class AuthResponse {
    private Boolean status;
    private String message;
    private String accessToken;
    private String refreshToken;
    private Date accessTokenExpireAt;
    private Date refreshTokenExpireAt;
}
