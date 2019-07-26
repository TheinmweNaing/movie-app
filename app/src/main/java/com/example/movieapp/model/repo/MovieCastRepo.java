package com.example.movieapp.model.repo;

import com.example.movieapp.model.dao.MovieCastDao;
import com.example.movieapp.model.entity.MovieCast;

import java.util.List;

public class MovieCastRepo {

    private MovieCastDao movieCastDao;

    public MovieCastRepo(MovieCastDao movieCastDao) {
        this.movieCastDao = movieCastDao;
    }

    public void save(MovieCast movieCast) {
        if (movieCast.getId() > 0) {
            movieCastDao.update(movieCast);
        } else {
            movieCastDao.insert(movieCast);
        }
    }

    public void delete(MovieCast movieCast) {
        movieCastDao.delete(movieCast);
    }

    public MovieCast getMovieCast(int id) {
        return movieCastDao.getCast(id);
    }

    public List<MovieCast> findAll() {
        List<MovieCast> list = movieCastDao.getAll();
        return list;
    }
}
