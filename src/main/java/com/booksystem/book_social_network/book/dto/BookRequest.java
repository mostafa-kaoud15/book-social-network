package com.booksystem.book_social_network.book.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookRequest {
    
    @NotBlank(message = "title should contains values")
    private String title;
    @NotBlank(message = "authorname should contains values")
    private String authorName;
    @NotBlank(message = "isbn should contains values")
    private String isbn;
    private String synopsis;
    @NotBlank(message = "bookCover should contains values")
    private String bookCover;
    @NotBlank(message = "archived should contains values")
    private boolean archived;
    @NotBlank(message = "shareable should contains values")
    private boolean shareable;

}
