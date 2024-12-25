package com.booksystem.book_social_network.authentication.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @NotBlank(message = "email must contains chars")
    @Email(message = "email must be valid")
    private String email;
    
    @NotBlank(message = "password must be contains chars")
    @Size(min = 3, max = 25)
    private String password;
}
