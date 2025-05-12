package PracticalExercises.InstructorCommision.Service;

import PracticalExercises.InstructorCommision.Exception.EntityAlreadyExistsException;
import PracticalExercises.InstructorCommision.Model.Instructor;
import PracticalExercises.InstructorCommision.Model.JuniorInstructor;
import PracticalExercises.InstructorCommision.Model.SeniorInstructor;
import PracticalExercises.InstructorCommision.Persistence.InstructorRepository;

import java.time.LocalDate;

public class InstructorRegistrationService {
    private final InstructorRepository repo;

    public InstructorRegistrationService(InstructorRepository repo) {
        this.repo = repo;
    }

    public void register(String id, String name, LocalDate birthDate, double revenueValue, String inCharge){
        Instructor mayBeInCharge = repo.findById(inCharge).orElse(null);

        if(mayBeInCharge==null){
            repo.save(new JuniorInstructor(id, birthDate, name, revenueValue, null));
            return;
        }

        SeniorInstructor seniorInstructor = mayBeInCharge instanceof JuniorInstructor
                ? ((JuniorInstructor) mayBeInCharge).promote()
                : (SeniorInstructor) mayBeInCharge;

        final JuniorInstructor newInstructor = new JuniorInstructor(id, birthDate, name, revenueValue, null);
        seniorInstructor.addSubordinates(newInstructor);

        repo.update(seniorInstructor);
        repo.save(newInstructor);
    }
}
