package PracticalExercises.CommissionManager.Service;

import PracticalExercises.CommissionManager.Model.Consultant;
import PracticalExercises.CommissionManager.Model.Employee;
import PracticalExercises.CommissionManager.Persistence.EmployeeRepository;

import java.util.Optional;
import java.util.stream.Collectors;

public class EmployeeReportService {
    private final EmployeeRepository repo;

    public EmployeeReportService(EmployeeRepository repo) {
        this.repo = repo;
    }

    public String reportOf(String id){
        final Optional<Employee> mayBeRoot = repo.findById(id);

        if(mayBeRoot.isEmpty()) return "Report is full of emptiness";

        final Employee root = mayBeRoot.get();
        String result = root.toString();

        if(root instanceof Consultant consultant){
            result +=consultant.getEmployees().stream()
                    .map(e->reportChildren(e, 1))
                    .collect(Collectors.joining());
        }
        return result;
    }

    private String reportChildren(Employee employee, int level){
        String result = "\n" + "\t" .repeat(level) + employee;

        if(employee instanceof Consultant consultant){
            result+=consultant.getEmployees().stream()
                    .map(e->reportChildren(e, level+1))
                    .collect(Collectors.joining());
        }
        return result;
    }
}
