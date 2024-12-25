package com.booksystem.book_social_network.authentication.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.booksystem.book_social_network.authentication.dto.LoginRequest;
import com.booksystem.book_social_network.authentication.dto.LoginResponse;
import com.booksystem.book_social_network.security.JwtService;
import com.booksystem.book_social_network.user.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserLoginService {
    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = (User)authentication.getPrincipal();
        String token = jwtService.generateJwt(user);
        return new LoginResponse(user.getEmail(), token);
    }
    
}
