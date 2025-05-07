package Aula11.Ex01.persistence;

import Aula11.Ex01.model.Employee;

import java.util.Optional;

public interface Repository <E, K>{
    void save(E entity);
    void update(E entity);
    Optional<E> findById(K id);

}
