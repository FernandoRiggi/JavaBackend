package Aula10.Ex01;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

public class Employee {
    private String id;
    private String name;
    private String jobTitle;
    private double salary;
    private LocalDate dateOfEmployment;
    private List<Paycheck> paychecks;

    public Employee(String id, String name, String jobTitle, double salary, LocalDate dateOfEmployment) {
        this.id = id;
        this.name = name;
        this.jobTitle = jobTitle;
        this.salary = salary;
        this.dateOfEmployment = dateOfEmployment;
    }

    public String getName() {
        return name;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getId() {
        return id;
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

    public List<Paycheck> getPaychecks() {
        return paychecks;
    }

    public double getYearsofService(){
        return LocalDate.now().getYear() - dateOfEmployment.getYear();
    }

    public void addPaycheck(LocalDate payday){paychecks.add(new Paycheck(payday, salary));}

    public void removePaycheck(Paycheck paycheck){paychecks.remove(paycheck);}

    public Iterator<Paycheck> iteratorPaycheck(){return paychecks.iterator();}
}
