package ru.jabki.firmplus.model;

import java.util.Objects;

public class Like {
    private final Long userId;
    private final Long filmId;

    public Like(Long userId, Long filmId) {
        this.userId = userId;
        this.filmId = filmId;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Like tmp = (Like) obj;
        return Objects.equals(tmp.getFilmId(), this.filmId) && Objects.equals(tmp.getUserId(), this.userId);
    }

    public Long getFilmId() {
        return this.filmId;
    }

    public Long getUserId() {
        return this.userId;
    }}
