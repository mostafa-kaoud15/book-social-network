package com.booksystem.book_social_network.authentication;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booksystem.book_social_network.authentication.dto.LoginRequest;
import com.booksystem.book_social_network.authentication.dto.LoginResponse;
import com.booksystem.book_social_network.authentication.dto.RegisterRequest;
import com.booksystem.book_social_network.authentication.dto.VerfiyRequest;
import com.booksystem.book_social_network.authentication.service.ResentVerificationEmailService;
import com.booksystem.book_social_network.authentication.service.UserActivationService;
import com.booksystem.book_social_network.authentication.service.UserLoginService;
import com.booksystem.book_social_network.authentication.service.UserRegisterService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRegisterService registerService;
    private final UserActivationService activationUserService;
    private final UserLoginService loginService;
    private final ResentVerificationEmailService resentVerificationService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterRequest request) {
        registerService.saveUser(request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("verfication token is sent");
    }

    @PostMapping("/verify")
    public ResponseEntity<String> activateUser(@RequestBody @Valid VerfiyRequest request) {
        activationUserService.activateUser(request);
        return ResponseEntity.ok("user is activated");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        LoginResponse loginResponse = loginService.login(request);
        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("/resent_verification")
    public ResponseEntity<String> postMethodName(@RequestParam String email) {
        resentVerificationService.resent(email);
        return ResponseEntity.ok("the code is resent");
    }

}
