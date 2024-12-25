package com.booksystem.book_social_network.book.bookservices;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.booksystem.book_social_network.book.Book;
import com.booksystem.book_social_network.book.BookRepository;
import com.booksystem.book_social_network.book.dto.BookMapper;
import com.booksystem.book_social_network.book.dto.BookRequest;
import com.booksystem.book_social_network.user.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaveBookService {
    private final BookMapper bookMapper;
    private final BookRepository bookRepo;

    public long execute(BookRequest request, Authentication authentication) {
        User owner = (User) authentication.getPrincipal();
        Book book = bookMapper.toBook(request);
        book.setOwner(owner);
        return bookRepo.save(book).getId();
    }
}
