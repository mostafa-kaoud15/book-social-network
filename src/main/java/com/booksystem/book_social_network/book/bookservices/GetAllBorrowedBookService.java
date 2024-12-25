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
public class GetAllBorrowedBookService {
    private final BookTransactionHistoryRepository bookTransactionRepo;
    private final BookMapper bookMapper;

    public PageResponse<BorrowedBookResponse> execute(int page, int size, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt"));
        Page<BookTransactionHistory> histories = bookTransactionRepo.findAllByUserEmail(user.getUsername(),
                pageable);
        List<BorrowedBookResponse> borrowedBookresponses = histories.stream().map(bookMapper::toBorrowedBookResponse)
                .toList();

        return new PageResponse<>(borrowedBookresponses, histories.getNumber(), histories.getSize(),
                histories.getTotalElements(), histories.getTotalPages(), histories.isFirst(), histories.isLast());
    }

}
