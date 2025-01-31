package com.booksystem.book_social_network.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    @Query("""
            select book from Book book
            where book.shareable = true
            and book.archived = false
            and book.createdBy != :email
            """)
    Page<Book> findAllDisplayableBooks(Pageable pageable, String email);
}
