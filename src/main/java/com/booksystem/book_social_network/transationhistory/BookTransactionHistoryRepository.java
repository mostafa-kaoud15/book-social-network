package com.booksystem.book_social_network.transationhistory;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookTransactionHistoryRepository extends JpaRepository<BookTransactionHistory, Long> {
    Page<BookTransactionHistory> findAllByUserEmail(String username, Pageable pageable);
    Optional<BookTransactionHistory> findByBookId(long id);

}
