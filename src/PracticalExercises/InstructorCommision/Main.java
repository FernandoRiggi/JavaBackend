package PracticalExercises.InstructorCommision;

import PracticalExercises.InstructorCommision.Persistence.InMemoryInstructorRepository;
import PracticalExercises.InstructorCommision.Persistence.InstructorRepository;
import PracticalExercises.InstructorCommision.Service.InstructorRegistrationService;
import PracticalExercises.InstructorCommision.Service.InstructorReportService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        final InstructorRepository repo = new InMemoryInstructorRepository();
        final var registrationService = new InstructorRegistrationService(repo);
        final var reportService = new InstructorReportService(repo);
        try {
            registrationService.register("201", "Marie Curie", LocalDate.parse("1867-11-07"), 10000.0, null);
            registrationService.register("202", "Ada Lovelace", LocalDate.parse("1815-12-10"), 5000.0, "201");
            registrationService.register("203", "Nikola Tesla", LocalDate.parse("1856-07-10"), 6000.0, "201");
            registrationService.register("204", "Alan Turing", LocalDate.parse("1912-06-23"), 4000.0, "202");
            registrationService.register("205", "John Nash", LocalDate.parse("1928-06-13"), 3000.0, "203");
            System.out.println(reportService.reportOf("201"));
        } catch (Exception e) {
            System.err.println("Erro ao registrar instrutores: " + e.getMessage());
        }
    }
}