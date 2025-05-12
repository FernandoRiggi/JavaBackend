package Lesson04.Ex02;

public interface Repository<K, T> {

    void save(T entity);

    T getById(K id);
}
