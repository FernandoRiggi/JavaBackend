package PracticalExercises.SecondPractical.ex01;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        final Guest GuestA = new Guest("045.3423.32.22", "Jao@gmail.com", "jao");
        final Guest GuestB = new Guest("045.3423.32.12", "maria@gmail.com", "maria");
        Room[] rooms = new Room[]{
                new Room(1, 200.00),
                new Room(1, 300.00),
                new Room(1, 400.00),
                new Room(1, 500.00)
        };

        Hotel hotel = new Hotel("IFSP", rooms);

        LocalDate today = LocalDate.now();
        final Reservation reservationA = hotel.makeReservation(GuestA, 1, today, today.plusDays(3));
        System.out.println(reservationA.asString());

        System.out.println("Overlapping before");
        final Reservation reservationB = hotel.makeReservation(GuestB, 1, today.minusDays(1), today.plusDays(2));
        System.out.println(reservationB == null ? "Overbook" : reservationB.asString());

        System.out.println("Overlapping After");
        final Reservation reservationC = hotel.makeReservation(GuestB, 1, today.plusDays(1), today.plusDays(5));
        System.out.println(reservationB == null ? "Overbook" : reservationB.asString());

        System.out.println("Overlapping After");
        final Reservation reservationD = hotel.makeReservation(GuestB, 1, today.plusDays(1), today.plusDays(2));
        System.out.println(reservationB == null ? "Overbook" : reservationB.asString());

        final Reservation reservation = hotel.cancelReservation(reservationA.getId());
        System.out.println("Cancelling: " + reservation.getId());

        System.out.println("Not overlapping before anymore");
        final Reservation reservationX = hotel.makeReservation(GuestB, 1, today.minusDays(1), today.plusDays(5));
        System.out.println(reservationB == null ? "Overbook" : reservationB.asString());

        System.out.println(hotel.getAvailableRoomsAt(today.plusDays(1)));


    }
}
