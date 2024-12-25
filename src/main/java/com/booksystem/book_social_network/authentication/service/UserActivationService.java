package com.booksystem.book_social_network.authentication.service;

import org.springframework.stereotype.Service;

import com.booksystem.book_social_network.authentication.dto.VerfiyRequest;
import com.booksystem.book_social_network.exception.InvalidVerificationCodeException;
import com.booksystem.book_social_network.exception.UserAlreadyVerifiedException;
import com.booksystem.book_social_network.token.VerificationToken;
import com.booksystem.book_social_network.token.VerificationTokenRepository;
import com.booksystem.book_social_network.user.User;
import com.booksystem.book_social_network.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserActivationService {
    private final VerificationTokenRepository verificationCodeRepo;
    private final UserRepository userRepo;

    public void activateUser(VerfiyRequest request) {
        // get token from the database
        // validate token (email and its email, isexpired, user is enabled or not)
        // enable and save the user
        String verificationCode = request.getVerificationCode();
        VerificationToken verificationToken = verificationCodeRepo.getByToken(verificationCode)
                .orElseThrow(() -> new InvalidVerificationCodeException(verificationCode));

        verifyToken(request.getEmail(), verificationToken);
        enableUserAccount(verificationToken.getUser());
    }

    public void verifyToken(String email, VerificationToken verificationToken) {
        User user = verificationToken.getUser();
        if (!user.getEmail().equals(email)) {
            throw new InvalidVerificationCodeException("Email mismatch for verification code: " + verificationToken.getToken());
        }
        if (verificationToken.isExpired()) {
            throw new InvalidVerificationCodeException("Verification code is expired: " + verificationToken.getToken());
        }
        if (user.isEnabled()) {
            throw new UserAlreadyVerifiedException("user is already verfiyed : " + user.getEmail());
        }
    }

    public void enableUserAccount(User user) {
        user.setEnabled(true);
        userRepo.save(user);
    }
}
