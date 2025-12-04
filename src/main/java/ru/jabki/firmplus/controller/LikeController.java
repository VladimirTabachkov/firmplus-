package ru.jabki.firmplus.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.jabki.firmplus.model.Like;
import ru.jabki.firmplus.service.LikeService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/like")
@Tag(name = "Лайки фильму")
public class LikeController {
    private LikeService likeService;

    public LikeController(LikeService likes) {
        this.likeService = likes;
    }

    @PostMapping
    @Operation(summary = "Лайк фильму")
    public void addLike(@RequestBody final Like like) {
        likeService.addLikeToFilm(like.getFilmId(), like.getUserId());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить лайки по фильму")
    public List<Like> getById(@PathVariable("id") String filmId) {
        return likeService.getLikes(Long.parseLong(filmId));
    }

    @DeleteMapping
    @Operation(summary = "Удалить лайк")
    public void delete(@RequestParam(required = true) String filmId, @RequestParam(required = true) String userId) {
        likeService.removeLikeFromFilm(Long.parseLong(filmId), Long.parseLong(userId));
    }
}