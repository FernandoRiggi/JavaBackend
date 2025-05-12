package PracticalExercises.InstructorCommision.Model;

import java.time.LocalDate;
import java.util.Objects;

public sealed abstract class Instructor permits JuniorInstructor, SeniorInstructor {
    protected final String id;
    protected final String name;
    protected final LocalDate birthDate;
    protected final double revenueGenerated;
    private final SeniorInstructor seniorInCharge;

    public Instructor(String id, LocalDate birthDate, String name, double revenueGenerated, SeniorInstructor seniorInCharge) {
        this.id = id;
        this.birthDate = birthDate;
        this.name = name;
        this.revenueGenerated = revenueGenerated;
        this.seniorInCharge = seniorInCharge;
    }

    public abstract double getCommission();

    @Override
    public String toString() {
        return String.format("[%s] %s | Birthday: %s | Commission: %.2f ", id, name, birthDate, getCommission());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Instructor that = (Instructor) o;
        return Double.compare(revenueGenerated, that.revenueGenerated) == 0 && Objects.equals(id, that.id)
                && Objects.equals(name, that.name) && Objects.equals(birthDate, that.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthDate, revenueGenerated);
    }

    public SeniorInstructor getSeniorInCharge() {
        return seniorInCharge;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public double getRevenueGenerated() {
        return revenueGenerated;
    }
}
