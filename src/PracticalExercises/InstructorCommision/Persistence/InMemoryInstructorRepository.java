package PracticalExercises.InstructorCommision.Persistence;

import PracticalExercises.InstructorCommision.Exception.EntityAlreadyExistsException;
import PracticalExercises.InstructorCommision.Model.Instructor;
import PracticalExercises.InstructorCommision.Model.SeniorInstructor;

import java.util.*;

public class InMemoryInstructorRepository implements InstructorRepository{
    Map<String, Instructor> db = new LinkedHashMap<>();

    @Override
    public void save(Instructor entity) {
        if (db.containsKey(entity.getId())) {
            throw new EntityAlreadyExistsException("Entity with id already exists: " + entity.getId());
        }
        db.put(entity.getId(), entity);
    }

    @Override
    public void update(Instructor entity) {
        if(!db.containsKey(entity.getId()))
            throw new NoSuchElementException("Entity with id not found" + entity.getId());
        db.put(entity.getId(), entity);
    }

    @Override
    public Optional<Instructor> findById(String id) {
        Collection<Instructor> children = db.values().stream()
                .filter(e->Objects.nonNull(e.getSeniorInCharge()))
                .filter(e->e.getSeniorInCharge().getId().equals(id))
                .toList();

        final Instructor instructor = db.get(id);

        if(children.isEmpty()) return Optional.ofNullable(instructor);

        final SeniorInstructor seniorInstructor = (SeniorInstructor) instructor;

        children.forEach(e-> seniorInstructor.addSubordinates(findById(e.getId()).orElseThrow()));

        return Optional.ofNullable(seniorInstructor);
    }
}
