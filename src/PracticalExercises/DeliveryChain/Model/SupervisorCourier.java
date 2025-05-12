package PracticalExercises.DeliveryChain.Model;

import java.util.LinkedHashSet;
import java.util.Set;

public final class SupervisorCourier extends Courier {
    private final Set<Courier> subordinates;

    public SupervisorCourier(String id, int deliveries, String name, SupervisorCourier supervisorInCharge, LeaderCourier leaderInCharge) {
        super(id, deliveries, name, supervisorInCharge, leaderInCharge);
        this.subordinates = new LinkedHashSet<>();
    }

    public void addSubordinate(Courier c){
        subordinates.add(c);
    }

    public void removeSubordinate(Courier c){
        subordinates.remove(c);
    }

    public Set<Courier> getSubordinates(){
        return new LinkedHashSet<>(subordinates);
    }

    public LeaderCourier promote(){
        return new LeaderCourier(id, deliveries, name, null, null);
    }

    @Override
    public double getCommission() {
        double directDeliveriesCommission = getDeliveries() * 2;

        double subordinateCommission = subordinates.stream()
                .mapToDouble(Courier::getCommission)
                .map(value -> value * 0.2)
                .reduce(getTotalDeliveries() * 0.2, Double::sum);

        return directDeliveriesCommission + subordinateCommission;
    }

    @Override
    public int getTotalDeliveries() {
        return getDeliveries() + subordinates.stream()
                .mapToInt(Courier::getDeliveries)
                .sum();
    }
}
