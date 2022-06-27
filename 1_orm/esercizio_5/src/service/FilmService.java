package service;

import model.Film;

import java.util.List;
import java.util.Optional;

public interface FilmService {
    void createTable();
    void deleteTable();

    void createFilm(final Film film);
    void deleteFilm(int id);
    void updateTitoloFilm(int id, String nuovoTitolo);
    Optional<Film> findFilmById(int id);
    List<Film> findAll();
}