import model.Actor;
import model.Genre;
import model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import repository.ActorRepository;
import repository.GenreRepository;
import repository.MovieRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        final SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Movie.class)
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Genre.class)
                .buildSessionFactory();

        final Session session = sessionFactory.openSession();


        final MovieRepository movieRepository = new MovieRepository(session.getSession());
        final ActorRepository actorRepository = new ActorRepository(session.getSession());

        Actor savedActor = actorRepository.save(new Actor("John", "Smith", 1970, null));

        Movie movie = new Movie("Predator", 1980, Collections.singletonList(savedActor), null);

        movieRepository.save(movie);
        System.out.println(movieRepository.findAllWithActors().get(0).getActors().size());


        //SOLUZIONE ALTERNATIVA
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("esercizio_1");
        EntityManager em = emf.createEntityManager();

        final GenreRepository genreRepository = new GenreRepository(em);
        genreRepository.save(new Genre("Horror", Collections.singletonList(movie)));
        System.out.println(genreRepository.findAll().size());

    }
}