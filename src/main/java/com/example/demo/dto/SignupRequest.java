package com.example.demo.dto;

import com.example.demo.entity.Role;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class SignupRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
