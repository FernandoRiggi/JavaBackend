package PracticalExercises.CommissionManager.Model;

import java.time.LocalDate;
import java.util.Objects;

public sealed abstract class Employee permits Reseller, Consultant {
    protected final String id;
    protected final String name;
    protected final LocalDate birthDate;
    protected final double soldValue;
    protected final Consultant consultantInCharge;

    public Employee(String id, String name, LocalDate birthDate, double soldValue, Consultant consultantInCharge) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.soldValue = soldValue;
        this.consultantInCharge = consultantInCharge;
    }

    public abstract double getComission();

    @Override
    public String toString() {
        return String.format("[%s] %s |Birthday: %s | Amount in sale: U$%.2f | Comission: U$%.2f",
                id, name, birthDate, soldValue, getComission() );
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Double.compare(soldValue, employee.soldValue) == 0 && Objects.equals(id, employee.id)
                && Objects.equals(name, employee.name) && Objects.equals(birthDate, employee.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthDate, soldValue);
    }

    public Consultant getConsultantInCharge() {
        return consultantInCharge;
    }

    public double getSoldValue() {
        return soldValue;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
