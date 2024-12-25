package com.booksystem.book_social_network.authentication.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.booksystem.book_social_network.authentication.dto.RegisterRequest;
import com.booksystem.book_social_network.token.VerificationEmailSender;
import com.booksystem.book_social_network.user.User;
import com.booksystem.book_social_network.user.UserRepository;
import com.booksystem.book_social_network.user.role.Role;
import com.booksystem.book_social_network.user.role.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserRegisterService {
    private final RoleRepository roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepo;
    private final VerificationEmailSender verificationEmailSender;

    public void saveUser(RegisterRequest request) {
        Role role = roleRepo.getByName("USER").orElseThrow(() -> new IllegalArgumentException("role wiht name USER not found"));
        User user = User.builder().firstname(request.getFirstname())
        .lastname(request.getLastname())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .roles(List.of(role))
        .enabled(false)
        .build();
        userRepo.save(user);
        verificationEmailSender.sentVerficationEmail(user);
        
    }




    
}
