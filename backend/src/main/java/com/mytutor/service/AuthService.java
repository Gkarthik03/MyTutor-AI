package com.mytutor.service;

import com.mytutor.entity.User;
import com.mytutor.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public AuthService(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public User register(String userId, String employeeId, String name, String email, String password) {
        String normalizedUserId = userId.trim();
        String normalizedEmployeeId = employeeId.trim();
        String normalizedName = name.trim();
        String normalizedEmail = email.trim().toLowerCase();

        if (repo.existsById(normalizedUserId)) {
            throw new IllegalArgumentException("User ID already exists.");
        }

        if (repo.existsByEmail(normalizedEmail)) {
            throw new IllegalArgumentException("Email is already registered.");
        }

        if (password.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters.");
        }

        User user = new User();
        user.setUserId(normalizedUserId);
        user.setEmployeeId(normalizedEmployeeId);
        user.setName(normalizedName);
        user.setEmail(normalizedEmail);
        user.setPassword(encoder.encode(password));

        return repo.save(user);
    }
}
