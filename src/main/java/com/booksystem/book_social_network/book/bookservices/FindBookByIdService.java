package com.booksystem.book_social_network.book.bookservices;

import org.springframework.stereotype.Service;

import com.booksystem.book_social_network.book.BookRepository;
import com.booksystem.book_social_network.book.dto.BookMapper;
import com.booksystem.book_social_network.book.dto.BookResponse;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FindBookByIdService {
    private final BookMapper bookMapper;
    private final BookRepository bookRepo;

    public BookResponse execute(long id) {
        return bookRepo.findById(id)
                .map(bookMapper::toBookResponse)
                .orElseThrow(() -> new EntityNotFoundException("book not found : " + id));

    }
}
