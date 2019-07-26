package com.example.movieapp.model.repo;

import android.text.method.MovementMethod;

import com.example.movieapp.model.dao.GenreDao;
import com.example.movieapp.model.dao.MovieDao;
import com.example.movieapp.model.entity.Movie;

import java.util.List;

public class MovieRepo {

    private MovieDao movieDao;
    private GenreDao genreDao;

    public MovieRepo(MovieDao movieDao, GenreDao genreDao) {
        this.movieDao = movieDao;
        this.genreDao = genreDao;
    }

    public void save(Movie movie) {
        if (movie.getId() > 0) {
            movieDao.update(movie);
        } else {
            movieDao.insert(movie);
        }
    }

    public void delete(Movie movie) {
        movieDao.delete(movie);
    }

    public Movie getMovie(int id) {
        return movieDao.getMovie(id);
    }

    public List<Movie> findAll() {
        List<Movie> list = movieDao.getAll();
        for (Movie movie : list) {
            movie.setGenre(genreDao.getGenre(movie.getGenreId()));
        }
        return list;
    }
}
