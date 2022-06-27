import model.Film;
import service.FilmService;
import service.FilmServiceImpl;

import java.sql.*;

public class JdbcApplication {

    private static final String DRIVER = "com.mysql.jc.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/movies";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName(DRIVER);
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            final FilmService filmService = new FilmServiceImpl(connection);
            filmService.createTable();
            filmService.createFilm(new Film("Back to the futureee with typo", "Fantasy", 1985));
            filmService.createFilm(new Film("Alien", "Fantasy", 1980));
            filmService.findFilmById(1).ifPresent(System.out::println); // displayed one record
            filmService.updateTitoloFilm(1, "Back to the future");
            filmService.findAll().forEach(System.out::println); // displayed two records
            filmService.deleteFilm(2);
            System.out.println(filmService.findAll().size()); // one row left in the table
            filmService.deleteTable();
        } catch (final SQLException exp) {
            System.err.println("Oops, something went wrong");
        }
    }
}