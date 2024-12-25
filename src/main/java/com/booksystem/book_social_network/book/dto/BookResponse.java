package com.booksystem.book_social_network.book.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {
    private String title;
    private String authorName;
    private String isbn;
    private String synopsis;
    private String owner;
    private String cover;
    private double rate;
    private boolean archived;
    private boolean shareable;


}
