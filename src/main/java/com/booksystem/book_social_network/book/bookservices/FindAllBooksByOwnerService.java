package com.booksystem.book_social_network.book.bookservices;

import static com.booksystem.book_social_network.book.BookSpecification.withOwnerUsername;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.booksystem.book_social_network.book.Book;
import com.booksystem.book_social_network.book.BookRepository;
import com.booksystem.book_social_network.book.dto.BookMapper;
import com.booksystem.book_social_network.book.dto.BookResponse;
import com.booksystem.book_social_network.common.PageResponse;
import com.booksystem.book_social_network.user.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FindAllBooksByOwnerService {
    private final BookRepository bookRepo;
    private final BookMapper bookMapper;

    public PageResponse<BookResponse> execute(int page, int size, Authentication authentication) {

        User owner = (User) authentication.getPrincipal();
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Book> books = bookRepo.findAll(withOwnerUsername(owner.getUsername()), pageable);
        List<BookResponse> bookResponses = books.stream().map(bookMapper::toBookResponse).toList();
        return new PageResponse<>(bookResponses, books.getNumber(), books.getSize(), books.getTotalElements(),
                books.getTotalPages(), books.isFirst(), books.isLast());
    }

}
