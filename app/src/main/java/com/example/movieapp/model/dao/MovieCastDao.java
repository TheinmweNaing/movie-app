package com.example.movieapp.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.movieapp.model.entity.MovieCast;

import java.util.List;

@Dao
public interface MovieCastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MovieCast movieCast);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(MovieCast movieCast);

    @Delete
    void delete(MovieCast movieCast);

    @Query("SELECT * FROM MovieCast WHERE id = :id LIMIT 1")
    MovieCast getCast(int id);

    @Query("SELECT * FROM MovieCast")
    List<MovieCast> getAll();
}
