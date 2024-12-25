package com.booksystem.book_social_network.token;

import org.springframework.stereotype.Service;

import com.booksystem.book_social_network.email.EmailService;
import com.booksystem.book_social_network.user.User;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VerificationEmailSender {
    private final EmailService mailService;
    private final VerificationTokenService verificationService;

    public void sentVerficationEmail(User user) {
        String verificationCode = verificationService.generateAndSaveVerificationCode(user);
        String to = user.getEmail();
        String subject = "EMAIL VEIRFICATION";
        String emailContent = createMessageContent(verificationCode);
        try {
            mailService.sentEmail(to, subject, emailContent);
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }

    private String createMessageContent(String verificationCode) {
        return "<html>"
                + "<body style=\"font-family: Arial, sans-serif;\">"
                + "<div style=\"background-color: #f5f5f5; padding: 20px;\">"
                + "<h2 style=\"color: #333;\">Welcome to our app!</h2>"
                + "<p style=\"font-size: 16px;\">Please enter the verification code below to continue:</p>"
                + "<div style=\"background-color: #fff; padding: 20px; border-radius: 5px; box-shadow: 0 0 10px rgba(0,0,0,0.1);\">"
                + "<h3 style=\"color: #333;\">Verification Code:</h3>"
                + "<p style=\"font-size: 18px; font-weight: bold; color: #007bff;\">" + verificationCode + "</p>"
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>";
    }

}
