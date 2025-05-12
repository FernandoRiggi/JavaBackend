package Lesson03.Ex02;

import java.time.LocalDate;

public final class FullTimeEmployee extends Employee {
    private double monthlySalary;
    public FullTimeEmployee(String id, String name, String jobTitle, LocalDate dateOfEmployment, double monthlySalary) {
        super(id, name, jobTitle, dateOfEmployment);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double Salary(){
        return monthlySalary;
    }

}
