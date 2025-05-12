package PracticalExercises.DigitalLibrary.Persistence;

import java.util.Collection;
import java.util.Optional;

public interface Repository <E, K>{
    void save(E entity);
    void update(E entity);
    Optional<E> findById(K id);
    void delete(E entity);
    Collection<E> findAll();
}
