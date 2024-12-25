package com.booksystem.book_social_network.book;

import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {

    public static Specification<Book> withOwnerUsername(String username) {
        if (username == null) {
            throw new IllegalArgumentException("Username cannot be null");
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("createdBy"), username);

    }

}
