package com.booksystem.book_social_network.authentication.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerfiyRequest {
    @NotBlank(message = "email must contains chars")
    @Email(message = "email must be valid")
    private String email;
    @NotBlank(message = "verification code must contains the codes")
    private String verificationCode;
}
