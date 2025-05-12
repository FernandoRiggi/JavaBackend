package PracticalExercises.InstructorCommision.Model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public final class SeniorInstructor extends Instructor {
    private final Set<Instructor> subordinates;

    public SeniorInstructor(String id, LocalDate birthDate, String name, double revenueGenerated, SeniorInstructor seniorInCharge) {
        super(id, birthDate, name, revenueGenerated, seniorInCharge);
        this.subordinates = new HashSet<>();
    }

    public void addSubordinates(Instructor instructor){
        subordinates.removeIf(i -> i.getId().equals(instructor.getId()));
        subordinates.add(instructor);
    }

    public Set<Instructor> getSubordinates(){
        return new HashSet<>(subordinates);
    }

    @Override
    public double getCommission() {
        return subordinates.stream()
                .mapToDouble(Instructor::getCommission)
                .map(value -> value * 0.4)
                .reduce(getRevenueGenerated() * 0.20, Double::sum);
    }


}
