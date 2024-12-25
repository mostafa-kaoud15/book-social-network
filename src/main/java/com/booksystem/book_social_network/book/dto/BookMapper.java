package com.booksystem.book_social_network.book.dto;

import org.springframework.stereotype.Service;

import com.booksystem.book_social_network.book.Book;
import com.booksystem.book_social_network.transationhistory.BookTransactionHistory;

@Service

public class BookMapper {
    public Book toBook(BookRequest request) {

        return Book.builder()
                .authorName(request.getAuthorName())
                .archived(request.isArchived())
                .bookCover(request.getBookCover())
                .isbn(request.getIsbn())
                .title(request.getTitle())
                .shareable(request.isShareable())
                .synopsis(request.getSynopsis())
                .build();
    }

    public BookResponse toBookResponse(Book book) {
        return BookResponse.builder()
                .title(book.getTitle())
                .authorName(book.getAuthorName())
                .isbn(book.getIsbn())
                .synopsis(book.getSynopsis())
                .rate(book.getRate())
                .archived(book.isArchived())
                .shareable(book.isShareable())
                .owner(book.getOwner().getFullName())
                .cover(book.getBookCover())
                .build();
    }

    public BorrowedBookResponse toBorrowedBookResponse(BookTransactionHistory history) {
        Book book = history.getBook();
        return BorrowedBookResponse.builder()
                .id(history.getId())
                .title(book.getTitle())
                .authorName(book.getAuthorName())
                .bookCover(book.getBookCover())
                .isbn(book.getIsbn())
                .synopsis(book.getSynopsis())
                .reatured(history.isReturned())
                .returnApproved(history.isReturnedApproved())
                .build();
    }
}
