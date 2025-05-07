package Aula11.Ex01.persistence;

import Aula11.Ex01.exception.EntityAlreadyExistsException;
import Aula11.Ex01.model.Consultant;
import Aula11.Ex01.model.Employee;

import java.util.*;

public class InMemoryEmployeeRepository implements EmployeeRepository {
    private final Map<String, Employee> db = new LinkedHashMap<>();

    @Override
    public void save(Employee entity) {
        if(db.containsKey(entity.getId()))
            throw new EntityAlreadyExistsException("Entity with id is already registered: " + entity.getId());
        db.put(entity.getId(), entity);
    }

    @Override
    public void update(Employee entity) {
        if(db.replace(entity.getId(), entity)==null)
            throw new NoSuchElementException("Entity not found: " + entity.getId());
    }

    @Override
    public Optional<Employee> findById(String id) {
        final Collection<Employee> children = db.values().stream()
                .filter(e -> Objects.nonNull(e.getConsultantInCharge()))
                .filter(e -> e.getConsultantInCharge().getId().equals(id))
                .toList();

        final Employee employee = db.get(id);
        if(children.isEmpty()) return Optional.ofNullable(employee);

        final Consultant consultant = (Consultant) employee;

        children.forEach(e -> consultant.addEmployee(findById(e.getId()).orElseThrow()));

        return Optional.ofNullable(consultant);
    }
}
