package com.booksystem.book_social_network.token;

import java.time.LocalDateTime;

import com.booksystem.book_social_network.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @PrePersist
    public void initializeTimestamps() {
        createdAt = LocalDateTime.now();
        expiredAt = LocalDateTime.now().plusMinutes(15);
    }

    public VerificationToken(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public boolean isExpired() {
        return expiredAt.isBefore(LocalDateTime.now());
    }
}
