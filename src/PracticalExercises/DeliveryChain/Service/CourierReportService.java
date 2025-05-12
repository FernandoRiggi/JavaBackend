package PracticalExercises.DeliveryChain.Service;

import PracticalExercises.DeliveryChain.Model.Courier;
import PracticalExercises.DeliveryChain.Model.LeaderCourier;
import PracticalExercises.DeliveryChain.Model.SupervisorCourier;
import PracticalExercises.DeliveryChain.Persistence.CourierRepository;

import java.util.Optional;
import java.util.stream.Collectors;

public class CourierReportService {
    private final CourierRepository repo;

    public CourierReportService(CourierRepository repo) {
        this.repo = repo;
    }

    public String ReportOf(String id) {
        final Optional<Courier> mayBeRoot = repo.findById(id);

        if (mayBeRoot.isEmpty()) return "The report is full of emptiness";

        final Courier root = mayBeRoot.get();
        String result = root.toString();

        if (root instanceof LeaderCourier leader) {
            result += leader.getSubordinates().stream()
                    .map(e -> reportChildren(e, 1))
                    .collect(Collectors.joining());
        }
        return result;
    }

    private String reportChildren(Courier courier, int level) {
        String result = "\n" + "\t".repeat(level) + courier;

        if (courier instanceof LeaderCourier leader) {
            result += leader.getSubordinates().stream()
                    .map(e -> reportChildren(e, level + 1))
                    .collect(Collectors.joining());
        } else if (courier instanceof SupervisorCourier supervisor) {
            result += supervisor.getSubordinates().stream()
                    .map(e -> reportChildren(e, level + 1))
                    .collect(Collectors.joining());
        }

        return result;
    }
}