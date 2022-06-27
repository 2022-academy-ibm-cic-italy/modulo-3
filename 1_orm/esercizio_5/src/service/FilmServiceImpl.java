package service;

import exception.DatabaseException;
import model.Film;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FilmServiceImpl implements FilmService {

    private final Connection connection;

    public FilmServiceImpl(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createTable() {
        try (final Statement createTableStatement = connection.createStatement()) {
            final String createTableQuery = "CREATE TABLE IF NOT EXISTS FILM (id INTEGER AUTO_INCREMENT, " +
                    "titolo VARCHAR(255), " +
                    "genere VARCHAR(255), " +
                    "anno INTEGER, " +
                    "PRIMARY KEY (id))";
            createTableStatement.execute(createTableQuery);
        } catch (final SQLException exp) {
            throw new DatabaseException(exp);
        }
    }

    @Override
    public void deleteTable() {
        try (final Statement deleteStructureStatement = connection.createStatement()) {
            deleteStructureStatement.execute("DROP TABLE FILM");
        } catch (final SQLException exp) {
            throw new DatabaseException(exp);
        }
    }

    @Override
    public void createFilm(final Film film) {
        try (final PreparedStatement insertItemStatement = connection.prepareStatement("INSERT INTO FILM (titolo, genere, anno) VALUES (?, ?, ?)")) {
            insertItemStatement.setString(1, film.getTitolo());
            insertItemStatement.setString(2, film.getGenere());
            insertItemStatement.setInt(3, film.getAnno());
            insertItemStatement.executeUpdate();
        } catch (final SQLException exp) {
            throw new DatabaseException(exp);
        }
    }

    @Override
    public void deleteFilm(final int id) {
        try (final PreparedStatement deleteItemStatement = connection.prepareStatement("DELETE FROM FILM WHERE id = ?")) {
            deleteItemStatement.setInt(1, id);
            deleteItemStatement.executeUpdate();
        } catch (final SQLException exp) {
            throw new DatabaseException(exp);
        }
    }

    @Override
    public void updateTitoloFilm(final int id, final String nuovoTitolo) {
        try (PreparedStatement updateStatement = connection.prepareStatement("UPDATE FILM SET titolo = ? WHERE id = ?")) {
            updateStatement.setString(1, nuovoTitolo);
            updateStatement.setInt(2, id);
            updateStatement.executeUpdate();
        } catch (final SQLException exp) {
            throw new DatabaseException(exp);
        }
    }

    @Override
    public Optional<Film> findFilmById(final int id) {
        try (PreparedStatement searchStatement = connection.prepareStatement("SELECT * FROM FILM WHERE id = ?")) {
            searchStatement.setInt(1, id);
            final boolean searchResult = searchStatement.execute();
            if (searchResult) {
                final ResultSet foundMovie = searchStatement.getResultSet();
                if (foundMovie.next()) {
                    final String title = foundMovie.getString(2);
                    final String genre = foundMovie.getString(3);
                    final Integer yearOfRelease = foundMovie.getInt(4);
                    return Optional.of(new Film(title, genre, yearOfRelease));
                }
            }
            return Optional.empty();
        } catch (final SQLException exp) {
            throw new DatabaseException(exp);
        }
    }

    @Override
    public List<Film> findAll() {
        try (final Statement createTableStatement = connection.createStatement()) {
            final String findAllQuery = "SELECT * FROM FILM";
            final ResultSet moviesResultSet = createTableStatement.executeQuery(findAllQuery);
            final List<Film> movies = new ArrayList<>();
            while (moviesResultSet.next()) {
                final Integer id = moviesResultSet.getInt(1);
                final String title = moviesResultSet.getString(2);
                final String genre = moviesResultSet.getString(3);
                final Integer yearOfRelease = moviesResultSet.getInt(4);
                movies.add(new Film(title, genre, yearOfRelease));
            }
            return movies;
        } catch (final SQLException exp) {
            throw new DatabaseException(exp);
        }
    }
}