package com.booksystem.book_social_network.book.bookservices;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.booksystem.book_social_network.book.Book;
import com.booksystem.book_social_network.book.BookRepository;
import com.booksystem.book_social_network.transationhistory.BookTransactionHistory;
import com.booksystem.book_social_network.transationhistory.BookTransactionHistoryRepository;
import com.booksystem.book_social_network.user.User;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BorrowBookService {
    /*
     * check from the book is valid (is sharable, and not archieved, and book is not
     * borrowed)
     * save in history, book with its user
     * 
     */
    private final BookRepository bookRepo;
    private final BookTransactionHistoryRepository historyRepo;

    public long execute(long id, Authentication authentication) {
        Book book = bookRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("book is not found : " + id));
        if (!isBookAvailable(book)) {
            throw new IllegalArgumentException();
        }
        BookTransactionHistory history = BookTransactionHistory.builder()
                .book(book)
                .user((User) authentication.getPrincipal())
                .returned(false)
                .returnedApproved(false)
                .build();

        historyRepo.save(history);
        return book.getId();

    }

    private boolean isBookAvailable(Book book) {
        if (!isBookShareable(book) || isBookBorrowed(book)) {
            return false;
        }
        return true;
    }

    private boolean isBookBorrowed(Book book) {
        BookTransactionHistory history = historyRepo.findByBookId(book.getId()).orElse(null);
        if (history == null || history.isReturned() == true || history.isReturnedApproved() == true) {
            return false;
        }
        return true;
    }

    private boolean isBookShareable(Book book) {

        return (!book.isArchived()) && book.isShareable();
    }

}
