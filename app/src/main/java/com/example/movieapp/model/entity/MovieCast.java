package com.example.movieapp.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Objects;

@Entity(foreignKeys = {
        @ForeignKey(entity = Movie.class, parentColumns = "m_id", childColumns = "movie_id")
}, indices = @Index("movie_id"))
public class MovieCast {

    public enum Occupation {
        ACTOR, ACTRESS
    }

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int age;
    private LocalDate dateOfBirth;
    private Occupation occupation;

    @ColumnInfo(name = "movie_id")
    private int movieId;

    @Ignore
    private Movie movie;

    public MovieCast() {
        dateOfBirth = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = LocalDate.parse(dateOfBirth, DateTimeFormat.forPattern("yyyy/MM/dd"));
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieCast movieCast = (MovieCast) o;
        return id == movieCast.id &&
                age == movieCast.age &&
                movieId == movieCast.movieId &&
                name.equals(movieCast.name) &&
                dateOfBirth.equals(movieCast.dateOfBirth) &&
                occupation == movieCast.occupation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, dateOfBirth, occupation, movieId);
    }
}
