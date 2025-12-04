package ru.jabki.firmplus.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class Review {
    private final Long id;
    private final Long userId;
    private final Long filmId;
    private String review;
    private final LocalDateTime created;

    private static final AtomicLong counter = new AtomicLong(0);

    public Review(Long userId, Long filmId, String review) {
        this.id = counter.incrementAndGet();
        this.review = review;
        this.filmId = filmId;
        this.userId = userId;
        this.created = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Review review)) return false;
        return this.id.equals(review.id);
    }

    public Long getId() {
        return this.id;
    }

    public Long getFilmId() {
        return this.filmId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public LocalDateTime getCreated() {
        return this.created;
    }

    public String getReview() {
        return this.review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
