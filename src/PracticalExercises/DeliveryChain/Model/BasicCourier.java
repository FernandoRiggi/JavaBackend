package PracticalExercises.DeliveryChain.Model;

import javax.swing.*;

public final class BasicCourier extends Courier {
    public BasicCourier(String id, int deliveries, String name, SupervisorCourier supervisorInCharge, LeaderCourier leaderInCharge) {
        super(id, deliveries, name, supervisorInCharge, leaderInCharge);
    }

    public SupervisorCourier promote(){
        return new SupervisorCourier(id, deliveries, name, null, leaderInCharge);
    }

    @Override
    public double getCommission() {
        return getTotalDeliveries()*4;
    }

    @Override
    public int getTotalDeliveries() {
        return getDeliveries();
    }
}
