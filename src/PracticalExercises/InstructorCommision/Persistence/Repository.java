package PracticalExercises.InstructorCommision.Persistence;

import java.util.Optional;

public interface Repository<E, K> {
    void save(E entity);
    void update(E entity);
    Optional<E> findById(K id);
}
