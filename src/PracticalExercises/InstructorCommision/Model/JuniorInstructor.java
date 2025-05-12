package PracticalExercises.InstructorCommision.Model;

import java.time.LocalDate;

public final class JuniorInstructor extends Instructor {
    private SeniorInstructor seniorInCharge;

    public JuniorInstructor(String id, LocalDate birthDate, String name, double revenueGenerated, SeniorInstructor seniorInCharge) {
        super(id, birthDate, name, revenueGenerated, seniorInCharge);
    }

    @Override
    public double getCommission() {
        return getRevenueGenerated() * 0.2;
    }

    public SeniorInstructor promote(){
        return new SeniorInstructor(id, birthDate, name, revenueGenerated, seniorInCharge);
    }
}
