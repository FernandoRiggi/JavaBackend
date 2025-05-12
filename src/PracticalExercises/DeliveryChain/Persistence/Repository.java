package PracticalExercises.DeliveryChain.Persistence;

import java.util.Optional;

public interface Repository <K, T>{
    void save(T entity);
    void update(T entity);
    Optional<T> findById(K id);
    void delete(T entity);
}
