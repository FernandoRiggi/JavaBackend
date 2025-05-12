package PracticalExercises.DigitalLibrary.Persistence;

import PracticalExercises.DigitalLibrary.Exception.EntityAlreadyExistsException;
import PracticalExercises.DigitalLibrary.Model.Publication;

import java.util.*;

public class InMemoryPublicationRepository implements PublicationRepository {
    private static final Map<String, Publication> db = new LinkedHashMap<>();


    @Override
    public void save(Publication entity) {
        if(db.containsKey(entity.getId()))
            throw new EntityAlreadyExistsException("Entity with id is already registered: " + entity.getId());
        db.put(entity.getId(), entity);
    }

    @Override
    public void update(Publication entity) {
        if(!db.containsKey(entity.getId()))
            throw new NoSuchElementException("Entity not found: " + entity.getId());
        db.replace(entity.getId(), entity);
    }

    @Override
    public Optional<Publication> findById(String id) {
        if(db.isEmpty()) return Optional.empty();
        return db.values().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    @Override
    public void delete(Publication entity) {
        if(!db.containsKey(entity.getId()))
            throw new NoSuchElementException("Entity not found: " + entity.getId());
        db.remove(entity.getId());
    }

    @Override
    public Collection<Publication> findAll() {
        return db.values();
    }
}
