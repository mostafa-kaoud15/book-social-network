package com.booksystem.book_social_network.authentication.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    @NotNull(message = "first can't be null")
    @NotBlank(message = "firstname must be contains a chars")
    private String firstname;
    @NotNull(message = "lastname can't be null")
    @NotBlank(message = "firstname must be contains a chars")
    private String lastname;
    @NotNull(message = "email can't be null")
    @Email(message = "email must be valid")
    private String email;
    @NotBlank(message = "password must be contains chars")
    @Size(min = 3, max = 25)
    private String password;

}
