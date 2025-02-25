package com.booksystem.book_social_network.book.bookservices;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.booksystem.book_social_network.book.Book;
import com.booksystem.book_social_network.book.BookRepository;
import com.booksystem.book_social_network.exception.InvalidBookException;
import com.booksystem.book_social_network.transationhistory.BookTransactionHistory;
import com.booksystem.book_social_network.transationhistory.BookTransactionHistoryRepository;
import com.booksystem.book_social_network.user.User;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BorrowBookService {
  
    private final BookRepository bookRepo;
    private final BookTransactionHistoryRepository historyRepo;

    public long execute(long id, Authentication authentication) {
        Book book = bookRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("book is not found : " + id));

        if (!book.isShareable() || book.isArchived()) {
            throw new InvalidBookException("can't borrow this book : " + id);
        }

        if (historyRepo.isBookBorrowed(id)) {
            throw new InvalidBookException("book is already borrowed : " + id);
        }

        User user = (User) authentication.getPrincipal();
        
        if (book.getCreatedBy().equals(user.getEmail())){
            throw new InvalidBookException("you can't borrow your book : " + id);
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


}
