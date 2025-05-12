package Lesson05;

import java.time.LocalDate;
import java.util.List;

public class Company {
    private EmployeeService employeeService;
    private PaycheckService paycheckService;

    public Company() {
        this.employeeService = new EmployeeService();
        this.paycheckService = new PaycheckService(employeeService);
    }

    public void hire(Employee employee) {
        employeeService.hire(employee);
    }

    public void fire(String id) {
        employeeService.fire(id);
    }

    public List<Employee> getEmployees() {
        return employeeService.getAllEmployees();
    }

    public List<Employee> getEmployeesByJobTitle(String jobTitle) {
        return employeeService.getEmployeesByJobTitle(jobTitle);
    }

    public void pay(String id) {
        employeeService.findById(id).ifPresentOrElse(paycheckService::pay,
            () -> { throw new EmployeeNotFoundException("Employee does not exist"); });
    }

    public void increaseSalary(String id, double newSalary) {
        employeeService.increaseSalary(id, newSalary);
    }

    public double averageSalary(String jobTitle) {
        return employeeService.averageSalary(jobTitle);
    }

    public double averageSalary(LocalDate start, LocalDate end) {
        return paycheckService.averageSalaryInPeriod(start, end);
    }
}
