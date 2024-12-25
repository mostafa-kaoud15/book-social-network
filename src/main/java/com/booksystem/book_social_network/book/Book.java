package com.booksystem.book_social_network.book;

import java.util.List;

import com.booksystem.book_social_network.common.BaseEntity;
import com.booksystem.book_social_network.feedback.FeedBack;
import com.booksystem.book_social_network.transationhistory.BookTransactionHistory;
import com.booksystem.book_social_network.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Book extends BaseEntity {
    private String title;
    private String authorName;
    private String isbn;
    private String synopsis;
    private String bookCover;
    private boolean archived;
    private boolean shareable;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "book")
    private List<BookTransactionHistory> histories;

    @OneToMany(mappedBy = "book")
    private List<FeedBack> feedBacks;

    public double getRate() {
        return feedBacks.stream()
                .mapToDouble(FeedBack::getNote)
                .average().orElse(0.0);
    }

}
