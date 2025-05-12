package PracticalExercises.Taxes.persistence;

import PracticalExercises.Taxes.exception.EntityAlreadyExisistsException;
import PracticalExercises.Taxes.model.Declaracao;

import java.util.*;

public class InMemoryDeclaracaoRepository implements DeclaracaoRepositoy {
    private static final Map<String, Declaracao> db = new HashMap<>();

    @Override
    public void save(Declaracao entity) {
        if(db.containsKey(entity.getId()))
            throw new EntityAlreadyExisistsException("Entity with id is already registered:  " + entity.getId());
        db.put(entity.getId(), entity);
    }

    @Override
    public void update(Declaracao entity) {
        if(db.replace(entity.getId(), entity)==null)
            throw new NoSuchElementException("Entity not found: " + entity.getId());
        db.replace(entity.getId(), entity);
    }

    @Override
    public Optional<Declaracao> findById(Long id) {
        if(db.isEmpty()) return Optional.empty();       
        return db.values().stream()
                .filter(Objects::nonNull)
                .filter(d ->d.getId().equals(id))
                .findFirst();
    }

    @Override
    public void delete(Declaracao entity) {
        if(!db.containsKey(entity.getId()))
            throw new NoSuchElementException("Entity not found: " + entity.getId());
        db.remove(entity.getId());
    }

    @Override
    public Collection<Declaracao> findAll() {
        return db.values();
    }

}
