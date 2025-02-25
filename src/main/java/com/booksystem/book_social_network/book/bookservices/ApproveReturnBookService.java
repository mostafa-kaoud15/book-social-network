package com.booksystem.book_social_network.book.bookservices;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.booksystem.book_social_network.exception.OperationNotPermittedException;
import com.booksystem.book_social_network.transationhistory.BookTransactionHistory;
import com.booksystem.book_social_network.transationhistory.BookTransactionHistoryRepository;
import com.booksystem.book_social_network.user.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApproveReturnBookService {
    private final BookTransactionHistoryRepository historyRepo;
    public long approveReturnBook(long id, Authentication connectUser) {
        User user = (User)connectUser.getPrincipal();
        BookTransactionHistory history =  historyRepo.getHistoryByBookIdAndUser(id, user.getUsername()).orElseThrow(() -> new OperationNotPermittedException("can't approve return this book : " + id));
        history.setReturnedApproved(true);
        historyRepo.save(history);
        return id;
    }

}
