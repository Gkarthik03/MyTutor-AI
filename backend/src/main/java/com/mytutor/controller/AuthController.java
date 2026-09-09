package com.mytutor.controller;

import com.mytutor.dto.LoginRequest;
import com.mytutor.dto.RegisterRequest;
import com.mytutor.service.AuthService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService auth;

    public AuthController(AuthService a) {
        auth = a;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest r) {
        try {
            var u = auth.register(
                    r.userId(),
                    r.employeeId(),
                    r.name(),
                    r.email(),
                    r.password()
            );

            return ResponseEntity.status(201).body(Map.of(
                    "message", "Account created successfully.",
                    "userId", u.getUserId(),
                    "name", u.getName(),
                    "email", u.getEmail()
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }
}
