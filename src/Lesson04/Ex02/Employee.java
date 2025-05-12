package Lesson04.Ex02;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class Employee {
    private String id;
    private String name;
    private String jobTitle;
    private double salary;
    private LocalDate dateOfEmployment;

    public double getYearsofService(){
        return Period.between(dateOfEmployment, LocalDate.now()).getYears();
    }

    public double calculatBonus(){
        return salary + 0.1;
    }

    public Employee(String id, String name, String jobTitle, double salary, LocalDate dateOfEmployment) {
        this.id = id;
        this.name = name;
        this.jobTitle = jobTitle;
        this.salary = salary;
        this.dateOfEmployment = dateOfEmployment;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getDateOfEmployment() {
        return dateOfEmployment;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", salary=" + salary +
                ", dateOfEmployment=" + dateOfEmployment +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Double.compare(salary, employee.salary) == 0 && Objects.equals(id, employee.id) && Objects.equals(name, employee.name) && Objects.equals(jobTitle, employee.jobTitle) && Objects.equals(dateOfEmployment, employee.dateOfEmployment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, jobTitle, salary, dateOfEmployment);
    }
}
