package PracticalExercises.CommissionManager.Model;

import java.time.LocalDate;

public final class Reseller extends Employee {
    public Reseller(String id, String name, LocalDate birthDate, double soldValue, Consultant consultantInCharge) {
        super(id, name, birthDate, soldValue, consultantInCharge);
    }

    @Override
    public double getComission() {
        return soldValue * 0.15;
    }

    public Consultant promote(){
        return new Consultant(id, name, birthDate, soldValue, consultantInCharge);
    }
}
