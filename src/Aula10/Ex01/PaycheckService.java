package Aula10.Ex01;

import java.time.LocalDate;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class PaycheckService {
    private EmployeeService employeeService;
    private Employee employee;

    public PaycheckService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void pay(Employee employee) {
        if (employee == null) throw new IllegalArgumentException("Employee cannot be null");
        employee.addPaycheck(LocalDate.now());
    }

    public double averageSalaryInPeriod(LocalDate start, LocalDate end) {
        if (employee == null) throw new IllegalArgumentException("Employee cannot be null");
        if (start == null || end == null) throw new IllegalArgumentException("Start and end dates cannot be null");
        if (start.isAfter(end)) throw new IllegalArgumentException("Start date cannot be after end date");

        return getPaycheckStream(employee)
                .filter(p -> !p.getPayday().isBefore(start) && !p.getPayday().isAfter(end))
                .mapToDouble(Paycheck::getSalary)
                .average()
                .orElse(0.0);
    }

    private Stream<Paycheck> getPaycheckStream(Employee employee) {
        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(employee.iteratorPaycheck(), 0),
                false
        );
    }
}
