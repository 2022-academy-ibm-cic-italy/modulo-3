import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class Main {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Car car) {
        entityManager.persist(car); // se l'oggetto non esiste nel db, -> insert

        entityManager.merge(car); // update
    }

    public void findCarById(Integer id) {
        entityManager.find(Car.class, id);
    }

    public void findAllCar() {
        List<Car> cars = entityManager.createQuery("select c from CARS").getResultList();
    }

    public void delete(Car car) {
        entityManager.detach(car); //chiave primaria dentro oggetto
    }

}
