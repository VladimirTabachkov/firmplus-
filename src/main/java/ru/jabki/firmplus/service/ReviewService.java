package ru.jabki.firmplus.service;

import ru.jabki.firmplus.exception.ReviewException;
import ru.jabki.firmplus.model.Review;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReviewService {
    private List<Review> reviews;

    public ReviewService() {
        this.reviews = new ArrayList<>();
    }

    public void addReview(Long filmId, Long userId, String content) {
        if(!reviews.contains(new Review(userId, filmId, content))){
            reviews.add(new Review(userId, filmId, content));
        }
    }

    public void removeReview(Integer filmId, Long userId) {
        Review rev = reviews.stream().filter(f -> (Objects.equals(f.getFilmId(), filmId)) &&
                                                         (Objects.equals(f.getUserId(), userId))).findFirst().orElseThrow(() -> new ReviewException("Review not found"));
        reviews.remove(reviews.indexOf(rev));
    }

    public void updateReview(Integer filmId, Long userId, String content) {
        Review tmp = reviews.stream().filter(f -> (!(filmId == null) && Objects.equals(f.getFilmId(), filmId)) && (!(userId == null) && Objects.equals(f.getUserId(), userId))).findFirst()
                .orElseThrow(() -> new ReviewException("Не нашли отзыв для обновления"));
        reviews.set(reviews.indexOf(tmp), new Review(tmp.getUserId(), tmp.getFilmId(), content));
    }

    public List<Review> getReviews(Integer filmId) {
        return reviews.stream().filter(f -> (!(filmId == null) && Objects.equals(f.getFilmId(), filmId))).toList();
    }}
