package PracticalExercises.DeliveryChain.Model;

import java.util.Objects;

public sealed abstract class Courier permits BasicCourier, LeaderCourier, SupervisorCourier {
    protected final String id;
    protected final String name;
    protected final int deliveries;
    protected final SupervisorCourier supervisorInCharge;
    protected final LeaderCourier leaderInCharge;

    public Courier(String id, int deliveries, String name, SupervisorCourier supervisorInCharge, LeaderCourier leaderInCharge) {
        this.id = id;
        this.deliveries = deliveries;
        this.name = name;
        this.supervisorInCharge = supervisorInCharge;
        this.leaderInCharge = leaderInCharge;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s | total of deliveries %s", id, name, getTotalDeliveries());
    }


    public abstract double getCommission();
    public abstract int getTotalDeliveries();


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Courier courier = (Courier) o;
        return deliveries == courier.deliveries && Objects.equals(id, courier.id) && Objects.equals(name, courier.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, deliveries);
    }

    public SupervisorCourier getSupervisorInCharge() {
        return supervisorInCharge;
    }

    public LeaderCourier getLeaderInCharge() {
        return leaderInCharge;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDeliveries() {
        return deliveries;
    }
}
