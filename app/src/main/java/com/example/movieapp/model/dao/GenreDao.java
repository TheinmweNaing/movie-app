package com.example.movieapp.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.movieapp.model.entity.Genre;

import java.util.List;

@Dao
public interface GenreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Genre genre);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Genre genre);

    @Delete
    void delete(Genre genre);

    @Query("SELECT * FROM Genre WHERE id = :id LIMIT 1 ")
    Genre getGenre(int id);

    @Query("SELECT * FROM Genre")
    List<Genre> getAll();
}
