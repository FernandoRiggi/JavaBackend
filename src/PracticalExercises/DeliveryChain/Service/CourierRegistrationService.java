package PracticalExercises.DeliveryChain.Service;

import PracticalExercises.DeliveryChain.Exception.EntityAlreadyExistsException;
import PracticalExercises.DeliveryChain.Exception.InvalidDeliveryCountException;
import PracticalExercises.DeliveryChain.Model.BasicCourier;
import PracticalExercises.DeliveryChain.Model.Courier;
import PracticalExercises.DeliveryChain.Model.LeaderCourier;
import PracticalExercises.DeliveryChain.Model.SupervisorCourier;
import PracticalExercises.DeliveryChain.Persistence.CourierRepository;

public class CourierRegistrationService {
    private final CourierRepository repo;

    public CourierRegistrationService(CourierRepository repo) {
        this.repo = repo;
    }

    public void register(String id, int deliveries, String name, String supervisorId, String leaderId) {
        if (deliveries < 0) throw new InvalidDeliveryCountException("The number of deliveries cannot be lower than 0");

        if (repo.findById(id).isPresent()) {
            throw new EntityAlreadyExistsException("Courier with id already exists: " + id);
        }

        Courier leader = findLeader(leaderId);
        Courier supervisor = findSupervisor(supervisorId);

        if (leader == null) {
            repo.save(new LeaderCourier(id, deliveries, name, null, null));
        } else if (supervisor == null) {
            repo.save(new SupervisorCourier(id, deliveries, name, null, (LeaderCourier) leader));
        } else {
            promoteCourier(supervisor);
        }
    }


    private Courier findLeader(String leaderId) {
        return repo.findById(leaderId).orElse(null);
    }

    private Courier findSupervisor(String supervisorId){
        return repo.findById(supervisorId).orElse(null);
    }

    private void promoteCourier(Courier supervisor) {
        if (supervisor instanceof BasicCourier basicCourier) {
            Courier promotedCourier = basicCourier.promote();
            if (!repo.findById(promotedCourier.getId()).isPresent()) {
                repo.save(promotedCourier);
            }
        } else if (supervisor instanceof SupervisorCourier supervisorCourier) {
            Courier promotedCourier = supervisorCourier.promote();
            if (!repo.findById(promotedCourier.getId()).isPresent()) {
                repo.save(promotedCourier);
            }
        }
    }
}
