package Aula08.Ex02;

import java.time.LocalDate;

public final class PerHourEmployee extends Employee {
    private double hourlyRate;
    private int workedHours;
    public PerHourEmployee(String id, String name, String jobTitle, LocalDate dateOfEmployment, double hourlyRate, int workedHours) {
        super(id, name, jobTitle, dateOfEmployment);
        this.hourlyRate = hourlyRate;
        this.workedHours = workedHours;
    }

    @Override
    public double Salary() {
        return hourlyRate * workedHours;
    }

    @Override
    public String toString() {
        return super.toString() + " HourlyRate=" + hourlyRate + ", WorkedHours=" + workedHours;
    }
}
