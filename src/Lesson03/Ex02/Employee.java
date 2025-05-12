package Lesson03.Ex02;

import java.time.LocalDate;

public sealed abstract class Employee permits FullTimeEmployee, PerHourEmployee {
    private String id;
    private String name;
    private String jobTitle;
    private LocalDate dateOfEmployment;

    public Employee(String id, String name, String jobTitle, LocalDate dateOfEmployment) {
        this.id = id;
        this.name = name;
        this.jobTitle = jobTitle;
        this.dateOfEmployment = dateOfEmployment;
    }

    public abstract double Salary();

    @Override
    public String toString() {
        return String.format("%s ID=%s Name=%s JobTitle=%s Date of Employment=%s, Salary: %.2f", getClass().getSimpleName(), id, name, jobTitle, dateOfEmployment, Salary());
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

    public LocalDate getDateOfEmployment() {
        return dateOfEmployment;
    }
}
