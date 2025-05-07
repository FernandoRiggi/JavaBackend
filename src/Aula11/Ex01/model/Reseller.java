package Aula11.Ex01.model;

import java.time.LocalDate;

public final class Reseller extends Employee {
    public Reseller(String id, String name, LocalDate birthDate ,double soldValue, Consultant consultantInCharge) {
        super(id, name, birthDate,soldValue, consultantInCharge);
    }

    @Override
    public double getCommission() {
        return getSoldValue() * 0.15;
    }

    public Consultant promote(){
        return new Consultant(id, name, birthDate, soldValue, consultantInCharge);
    }
}
