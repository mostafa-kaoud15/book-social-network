package com.booksystem.book_social_network.token;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.booksystem.book_social_network.user.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VerificationTokenService {

    private final VerificationTokenRepository verificationTokenRepo;
    public String generateAndSaveVerificationCode(User user) {
        String code = generateVerficationCode(6);
        VerificationToken verficationToken = new VerificationToken(code, user);
        verificationTokenRepo.save(verficationToken);
        return code;
    }

    public String generateVerficationCode(int length) {
        String chars = "12356789abcdefjklmnopqs";
        Random rand = new Random();
        StringBuilder codeBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randIndex = rand.nextInt(chars.length());
            codeBuilder.append(chars.charAt(randIndex));
        }
        return codeBuilder.toString();
    }

}
