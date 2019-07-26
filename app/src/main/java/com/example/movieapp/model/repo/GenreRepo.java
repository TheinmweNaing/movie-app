package com.example.movieapp.model.repo;

import com.example.movieapp.model.dao.GenreDao;
import com.example.movieapp.model.entity.Genre;

import java.util.List;

public class GenreRepo {

    private GenreDao genreDao;

    public GenreRepo(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    public void save(Genre genre) {
        if (genre.getId() > 0) {
            genreDao.update(genre);
        } else {
            genreDao.insert(genre);
        }
    }

    public void delete(Genre genre) {
        genreDao.delete(genre);
    }

    public Genre getGenre(int id) {
        return genreDao.getGenre(id);
    }

    public List<Genre> findAll() {
        return genreDao.getAll();
    }
}
