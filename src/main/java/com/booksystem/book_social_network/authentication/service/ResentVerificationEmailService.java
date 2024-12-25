package com.booksystem.book_social_network.authentication.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.booksystem.book_social_network.exception.UserAlreadyVerifiedException;
import com.booksystem.book_social_network.token.VerificationEmailSender;
import com.booksystem.book_social_network.user.User;
import com.booksystem.book_social_network.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResentVerificationEmailService {
    private final UserRepository userRepo;
    private final VerificationEmailSender emailSender;
    public void resent(String email) {
        User user = userRepo.getByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
        if (user.isEnabled()) {
            throw new UserAlreadyVerifiedException();
        }
        emailSender.sentVerficationEmail(user);
    }
    
}
