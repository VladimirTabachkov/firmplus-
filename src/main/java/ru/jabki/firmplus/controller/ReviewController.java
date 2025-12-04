package ru.jabki.firmplus.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.jabki.firmplus.model.Review;
import ru.jabki.firmplus.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/review")
@Tag(name = "Комментарии к фильмам")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviews) {
        this.reviewService = reviews;
    }

    @PostMapping
    @Operation(summary = "Проревьить фильм")
    public void addReview(@RequestBody final Review review) {
        reviewService.addReview(review.getFilmId(), review.getUserId(), review.getReview());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить ревью по фильму")
    public List<Review> getById(@PathVariable("id") String filmId) {
        return reviewService.getReviews(Integer.parseInt(filmId));
    }

    @PatchMapping
    @Operation(summary = "Обновить ревью")
    public void delete(@RequestParam(required = true) String filmId, @RequestParam(required = true) String userId, @RequestParam(required = true) String content) {
        reviewService.updateReview(Integer.parseInt(filmId), Long.parseLong(userId), content);
    }

    @DeleteMapping
    @Operation(summary = "Удалить ревью")
    public void delete(@RequestParam(required = true) String filmId, @RequestParam(required = true) String userId) {
        reviewService.removeReview(Integer.parseInt(filmId), Long.parseLong(userId));
    }
}