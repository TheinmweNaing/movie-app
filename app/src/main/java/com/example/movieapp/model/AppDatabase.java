package com.example.movieapp.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.movieapp.model.dao.MovieCastDao;
import com.example.movieapp.model.dao.GenreDao;
import com.example.movieapp.model.dao.MovieDao;
import com.example.movieapp.model.entity.MovieCast;
import com.example.movieapp.model.entity.Genre;
import com.example.movieapp.model.entity.Movie;

@Database(entities = {
        Movie.class,
        MovieCast.class,
        Genre.class
}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();

    public abstract MovieCastDao castDao();

    public abstract GenreDao genreDao();
}
