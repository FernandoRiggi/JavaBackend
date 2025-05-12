package PracticalExercises.DeliveryChain.Persistence;

import PracticalExercises.DeliveryChain.Exception.EntityAlreadyExistsException;
import PracticalExercises.DeliveryChain.Model.Courier;
import PracticalExercises.DeliveryChain.Model.LeaderCourier;
import PracticalExercises.DeliveryChain.Model.SupervisorCourier;

import java.util.*;

public class InMemoryCourierRepository implements CourierRepository{
    private final Map<String, Courier> db = new LinkedHashMap<>();

    @Override
    public void save(Courier entity) {
        if(db.containsKey(entity.getId()))
            throw new EntityAlreadyExistsException("Entity with id already exists: " + entity.getId());
        db.put(entity.getId(), entity);
    }

    @Override
    public void update(Courier entity) {
        if(!db.containsKey(entity.getId()))
            throw new NoSuchElementException("Entity with id not found: " + entity.getId());
        db.put(entity.getId(), entity);
    }

    @Override
    public Optional<Courier> findById(String id) {
        final Courier courier = db.get(id);

        if(courier==null) return Optional.empty();

        if(isLeader(id)){
            LeaderCourier leader = (LeaderCourier) courier;
            final Collection<Courier> children = db.values().stream()
                    .filter(e->e.getLeaderInCharge()!=null)
                    .filter(e->e.getLeaderInCharge().getId().equals(id))
                    .toList();
            children.forEach(child->
                    findById(child.getId())
                        .ifPresent(leader::addSubordinate));
            return Optional.of(leader);
        }

        if(isSupervisor(id)){
            SupervisorCourier supervisor = (SupervisorCourier) courier;
            final Collection<Courier> children = db.values().stream()
                    .filter(e->e.getLeaderInCharge()!=null)
                    .filter(e->e.getSupervisorInCharge()!=null)
                    .filter(e->e.getSupervisorInCharge().getId().equals(id))
                    .toList();
            children.forEach(child->
                    findById(child.getId())
                            .ifPresent(supervisor::addSubordinate));
            return Optional.of(supervisor);
        }

        return Optional.of(courier);
    }

    private boolean isLeader(String id){
        return db.values().stream()
                .anyMatch(courier ->courier.getLeaderInCharge()==null &&
                                            Objects.equals(courier.getId(), id));
    }

    private boolean isSupervisor(String id){
        return db.values().stream()
                .anyMatch(courier->courier.getSupervisorInCharge()==null&&Objects.equals(courier.getId(), id));
    }

    @Override
    public void delete(Courier entity) {
        if(!db.containsKey(entity.getId()))
            throw new NoSuchElementException("Entity with id not found: "+ entity.getId());
        db.remove(entity.getId(), entity);
    }
}
