package com.example.movieapp.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

import java.util.Objects;

@Entity(foreignKeys = {
        @ForeignKey(entity = Genre.class, parentColumns = "id", childColumns = "genre_id")
}, indices = {@Index("genre_id")})
public class Movie {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "m_id")
    private int id;
    private String title;
    private LocalDate releaseDate;
    private String duration;
    private String country;
    private String director;
    private String language;

    @ColumnInfo(name = "genre_id")
    private int genreId;

    @Ignore
    private Genre genre;

    public Movie() {
        releaseDate = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = LocalDate.parse(releaseDate, DateTimeFormat.forPattern("yyyy/MM/dd"));
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id &&
                genreId == movie.genreId &&
                Objects.equals(title, movie.title) &&
                Objects.equals(releaseDate, movie.releaseDate) &&
                Objects.equals(duration, movie.duration) &&
                Objects.equals(country, movie.country) &&
                Objects.equals(director, movie.director) &&
                Objects.equals(language, movie.language) &&
                Objects.equals(genre, movie.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, releaseDate, duration, country, director, language, genreId, genre);
    }
}
