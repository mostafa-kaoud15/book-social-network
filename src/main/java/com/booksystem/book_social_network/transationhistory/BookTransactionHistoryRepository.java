package com.booksystem.book_social_network.transationhistory;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookTransactionHistoryRepository extends JpaRepository<BookTransactionHistory, Long> {
        Page<BookTransactionHistory> findAllByUserEmail(String username, Pageable pageable);

        @Query("""
                        select (count(*) > 0) from BookTransactionHistory history
                        where history.book.id = :id and history.returned = false and history.returnedApproved = false
                        """)
        boolean isBookBorrowed(long id);

        @Query("""
                        select history from BookTransactionHistory history
                        where history.book.createdBy = :user and history.returned = true
                        """)
        Page<BookTransactionHistory> findAllReturnedBooks(String user, Pageable pageable);

        @Query("""
                        select history from BookTransactionHistory history
                        where history.book.id = :bookId and history.user.email = :user and returned = false
                        """)
        Optional<BookTransactionHistory> findBookHistoryOfBookBorrowedByUser(Long bookId, String user);

        @Query("""
                        select history from BookTransactionHistory history
                        where history.book.id = :id
                        and history.book.createdBy = :user
                        and history.returned = true
                        and history.returnedApproved = false

                        """)

        Optional<BookTransactionHistory> getHistoryByBookIdAndUser(long id, String user);

}
