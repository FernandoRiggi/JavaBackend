package PracticalExercises.CommissionManager.Persistence;

import PracticalExercises.CommissionManager.Model.Consultant;
import PracticalExercises.CommissionManager.Model.Employee;
import PracticalExercises.DigitalLibrary.Exception.EntityAlreadyExistsException;

import java.util.*;

public class InMemomoryEmployeeRepository implements EmployeeRepository{
    Map<String, Employee> db = new LinkedHashMap();

    @Override
    public void save(Employee entity) {
        if(db.containsKey(entity.getId()))
            throw new EntityAlreadyExistsException("Entity with id already exists: " + entity.getId());
        db.put(entity.getId(), entity);
    }

    @Override
    public void update(Employee entity) {
        if(!db.containsKey(entity.getId()))
            throw new NoSuchElementException("Entity not found: " + entity.getId());
    }

    @Override
    public Optional<Employee> findById(String id) {
        final Collection<Employee> children = db.values().stream()
                .filter(e ->Objects.nonNull(e.getConsultantInCharge()))
                .filter(e ->e.getConsultantInCharge().getId().equals(id))
                .toList();

        final Employee employee = db.get(id);

        if(children.isEmpty()){
            return Optional.ofNullable(employee);
        }

        final Consultant consultant = (Consultant) employee;

        children.forEach(e ->consultant.addEmployes(findById(e.getId()).orElseThrow()));

        return Optional.ofNullable(consultant);
    }
}
