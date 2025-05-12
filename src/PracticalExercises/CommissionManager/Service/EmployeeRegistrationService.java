package PracticalExercises.CommissionManager.Service;

import PracticalExercises.CommissionManager.Model.Consultant;
import PracticalExercises.CommissionManager.Model.Employee;
import PracticalExercises.CommissionManager.Model.Reseller;
import PracticalExercises.CommissionManager.Persistence.EmployeeRepository;

import java.time.LocalDate;

public class EmployeeRegistrationService {
    private final EmployeeRepository repo;

    public EmployeeRegistrationService(EmployeeRepository repo) {
        this.repo = repo;
    }

    public void register(String id, String name, LocalDate birthDate, double soldValue, String inCharge){
        Employee mayBeInCharge= repo.findById(inCharge).orElse(null);

        if(mayBeInCharge==null){
            repo.save(new Reseller(id, name, birthDate, soldValue, null));
            return;
        }

        Consultant consultant = mayBeInCharge instanceof  Reseller
                ? ((Reseller) mayBeInCharge).promote()
                : (Consultant) mayBeInCharge;

        final Reseller newEmployee = new Reseller(id, name, birthDate, soldValue, null);
        consultant.addEmployes(newEmployee);

        repo.save(consultant);
        repo.save(newEmployee);
    }
}
