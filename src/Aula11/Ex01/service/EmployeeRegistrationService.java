package Aula11.Ex01.service;

import Aula11.Ex01.model.Consultant;
import Aula11.Ex01.model.Employee;
import Aula11.Ex01.model.Reseller;
import Aula11.Ex01.persistence.EmployeeRepository;

import java.time.LocalDate;

public class EmployeeRegistrationService {
    private final EmployeeRepository repo;

    public EmployeeRegistrationService(EmployeeRepository repo) {
        this.repo = repo;
    }

    public void register(String id, String name, LocalDate birthDate, double soldValue, String inCharge){
        Employee mayBeInCharge = repo.findById(inCharge).orElse(null);

        if(mayBeInCharge == null){
            repo.save(new Reseller(id, name, birthDate, soldValue, null));
            return;
        }

        Consultant consultant = mayBeInCharge instanceof  Reseller
                ? ((Reseller) mayBeInCharge).promote()
                : (Consultant) mayBeInCharge;

        final Reseller newEmployee = new Reseller(id, name, birthDate, soldValue, null);
        consultant.addEmployee(newEmployee);;

        repo.update(consultant);
        repo.save(newEmployee);
    }
}
