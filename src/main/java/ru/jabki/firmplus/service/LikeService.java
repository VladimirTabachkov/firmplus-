package ru.jabki.firmplus.service;

import org.springframework.stereotype.Service;
import ru.jabki.firmplus.model.Like;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class LikeService {
    private List<Like> likes;

    public LikeService() {
        this.likes = new ArrayList<>();
    }

    public void addLikeToFilm(Long filmId, Long userId) {
        if (!likes.contains(new Like(userId, filmId))){
            likes.add(new Like(userId, filmId));
        }
    }

    public void removeLikeFromFilm(Long filmId, Long userId) {
        likes.remove(new Like(userId, filmId));
    }

    public List<Like> getLikes(Long filmId) {
        return likes.stream().filter(f -> (!(filmId == null) && Objects.equals(f.getFilmId(), filmId))).toList();
    }
}