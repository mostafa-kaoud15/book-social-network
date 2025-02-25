package com.booksystem.book_social_network.book.bookservices;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.booksystem.book_social_network.exception.OperationNotPermittedException;
import com.booksystem.book_social_network.transationhistory.BookTransactionHistory;
import com.booksystem.book_social_network.transationhistory.BookTransactionHistoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReturnBookService {
    private final BookTransactionHistoryRepository historyRepo;

    public long execute(long id, Authentication connectedUser) {
        BookTransactionHistory bookHistory =  historyRepo.findBookHistoryOfBookBorrowedByUser(id, connectedUser.getName())
                .orElseThrow(() -> new OperationNotPermittedException("you don't borrow book with id : " + id));
        bookHistory.setReturned(true);
        historyRepo.save(bookHistory);
        return id;
    }

}
