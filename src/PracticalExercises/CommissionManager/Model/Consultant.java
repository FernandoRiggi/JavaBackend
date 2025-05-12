package PracticalExercises.CommissionManager.Model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public final class Consultant extends Employee {
    private final Set<Employee> employees;

    public Consultant(String id, String name, LocalDate birthDate, double soldValue, Consultant consultantInCharge) {
        super(id, name, birthDate, soldValue, consultantInCharge);
        employees = new HashSet<>();
    }

    @Override
    public double getComission() {
        return employees.stream()
                .mapToDouble(Employee::getComission)
                .map(value -> value * 0.3)
                .reduce(getSoldValue() * 0.15, Double::sum);
    }

    public void addEmployes(Employee employee){
        employees.removeIf(e -> e.getId().equals(employee.getId()));
        employees.add(employee);
    }

    public Set<Employee> getEmployees(){
        return new HashSet<>(employees);
    }
}
