package Aula10.Ex01;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeService {
    private final List<Employee> employees = new ArrayList<>();

    public void hire(Employee employee) {
        if (employee == null) throw new IllegalArgumentException("Employee cannot be null");
        if (employee.getId() == null) throw new IllegalArgumentException("Employee ID cannot be null");
        if (exists(employee.getId())) throw new DuplicateEmployeeException("Employee already exists");

        employees.add(employee);
    }

    public void fire(String id) {
        if (id == null) throw new IllegalArgumentException("Employee ID cannot be null");
        if (!exists(id)) throw new EmployeeNotFoundException("Employee does not exist");
        employees.removeIf(e -> e.getId().equals(id));
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }

    public List<Employee> getEmployeesByJobTitle(String jobTitle) {
        if (jobTitle == null) throw new IllegalArgumentException("Job title cannot be null");
        return employees.stream()
                .filter(e -> e.getJobTitle().equals(jobTitle))
                .toList();
    }

    public void increaseSalary(String id, double newSalary) {
        if (id == null) throw new IllegalArgumentException("Employee ID cannot be null");
        if (newSalary < 0) throw new IllegalArgumentException("New salary cannot be negative");

        Employee employee = employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFoundException("Employee does not exist"));

        employee.setSalary(newSalary);
    }

    public double averageSalary(String jobTitle) {
        if (jobTitle == null) throw new IllegalArgumentException("Job title cannot be null");
        return employees.stream()
                .filter(e -> e.getJobTitle().equals(jobTitle))
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0);
    }

    public Optional<Employee> findById(String id) {
        return employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    public boolean exists(String id) {
        return employees.stream().anyMatch(e -> e.getId().equals(id));
    }
}

