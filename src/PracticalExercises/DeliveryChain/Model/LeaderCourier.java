package PracticalExercises.DeliveryChain.Model;

import java.util.LinkedHashSet;
import java.util.Set;

public final class LeaderCourier extends Courier {
    private final Set<Courier> subordinates;

    public LeaderCourier(String id, int deliveries, String name, SupervisorCourier supervisorInCharge, LeaderCourier leaderInCharge) {
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

    @Override
    public double getCommission() {
    double supervisorCommissions = subordinates.stream()
            .filter(this::isLeaderByMe)
            .mapToDouble(Courier::getCommission)
            .map(value -> value * 0.3)
            .reduce(getTotalDeliveries() * 0.3, Double::sum);

        double courierCommissions = subordinates.stream()
                .filter(this::isSupervisedByMe)
                .mapToDouble(Courier::getCommission)
                .map(value -> value * 0.1)
                .reduce(getTotalDeliveries() * 0.1, Double::sum);

        return getTotalDeliveries() + supervisorCommissions + courierCommissions;
    }

    private boolean isLeaderByMe(Courier courier) {
        return courier.getLeaderInCharge().equals(this);
    }

    private boolean isSupervisedByMe(Courier courier) {
        return courier.getSupervisorInCharge() != null &&
                isLeaderByMe(courier.getSupervisorInCharge());
    }

    @Override
    public int getTotalDeliveries() {
        int subordinatesDeliveries = subordinates.stream()
                .filter(this::isLeaderByMe)
                .mapToInt(Courier::getDeliveries)
                .sum();

        int courierDeliveries = subordinates.stream()
                .filter(this::isSupervisedByMe)
                .mapToInt(Courier::getDeliveries)
                .sum();
        return getDeliveries() + subordinatesDeliveries + courierDeliveries;
    }
}
