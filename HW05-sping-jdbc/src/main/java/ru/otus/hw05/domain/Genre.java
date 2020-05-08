package ru.otus.hw05.domain;

public class Genre {
    private final long genreId;
    private final String genre;


    public Genre(long genreId, String genre) {
        this.genreId = genreId;
        this.genre = genre;
    }

    public Genre(String genre) {
        this.genreId = -1;
        this.genre = genre;
    }

    public long getGenreId() {
        return genreId;
    }

    public String getGenre() {
        return genre;
    }
}
