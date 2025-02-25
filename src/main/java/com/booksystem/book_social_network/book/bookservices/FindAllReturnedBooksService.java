package com.booksystem.book_social_network.book.bookservices;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.booksystem.book_social_network.book.dto.BookMapper;
import com.booksystem.book_social_network.book.dto.BorrowedBookResponse;
import com.booksystem.book_social_network.common.PageResponse;
import com.booksystem.book_social_network.transationhistory.BookTransactionHistory;
import com.booksystem.book_social_network.transationhistory.BookTransactionHistoryRepository;
import com.booksystem.book_social_network.user.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FindAllReturnedBooksService {
    private final BookTransactionHistoryRepository historyRepo;
    private final BookMapper bookMapper;

    public PageResponse<BorrowedBookResponse> execute(int page, int size, Authentication connectUser) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt"));
        User user = (User) connectUser.getPrincipal();
        Page<BookTransactionHistory> histories = historyRepo.findAllReturnedBooks(user.getUsername(), pageable);
        List<BorrowedBookResponse> borrowedBooks = histories.stream().map(bookMapper::toBorrowedBookResponse).toList();
        return new PageResponse<>(
                borrowedBooks,
                histories.getNumber(),
                histories.getSize(),
                histories.getTotalElements(),
                histories.getTotalPages(), histories.isFirst(),
                histories.isLast());
    }

}
