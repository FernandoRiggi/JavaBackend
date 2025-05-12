package PracticalExercises.InstructorCommision.Service;

import PracticalExercises.InstructorCommision.Model.Instructor;
import PracticalExercises.InstructorCommision.Model.SeniorInstructor;
import PracticalExercises.InstructorCommision.Persistence.InstructorRepository;
import com.sun.tools.jconsole.JConsoleContext;

import java.util.Optional;
import java.util.stream.Collectors;

public class InstructorReportService {
    private final InstructorRepository repo;


    public InstructorReportService(InstructorRepository repo) {
        this.repo = repo;
    }

    public String reportOf(String id){
        final Optional<Instructor> mayBeRoot = repo.findById(id);

        if(mayBeRoot.isEmpty()) return "Report is full of emptiness";

        final Instructor root = mayBeRoot.get();
        String result = root.toString();

        if(root instanceof SeniorInstructor senior){
            result += senior.getSubordinates().stream()
                    .map(e->reportChildren(e, 1))
                    .collect(Collectors.joining());
        }
        return result;
    }

    private String reportChildren(Instructor instructor, int level){
        String result = "\n" + "\t".repeat(level) + instructor;

        if(instructor instanceof SeniorInstructor senior){
            result+=senior.getSubordinates().stream()
                    .map(e->reportChildren(e, level+1))
                    .collect(Collectors.joining());
        }
        return result;
    }
}
