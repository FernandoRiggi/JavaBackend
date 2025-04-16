package Aula08.Ex02;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        final FullTimeEmployee fernando = new FullTimeEmployee("01", "fernando", "dev", LocalDate.now(), 2000);
        System.out.println(fernando);
        final PerHourEmployee filipe = new PerHourEmployee("02", "filipe", "dev", LocalDate.now(), 25, 200);
        System.out.println(filipe);
    }
}
