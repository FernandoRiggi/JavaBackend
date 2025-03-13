package Aula04.Ex02;

import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {

       Meeting meeting1 =  Meeting.of("POO", LocalTime.of(20, 30), LocalTime.of(21, 30));
       Meeting meeting2 = Meeting.of("BD", LocalTime.of(14, 30), LocalTime.of(17, 30));
       System.out.println(meeting1.durationInMinutes());

       Schedule schedule1 = Schedule.of(LocalDate.of(2020, 1, 1), LocalTime.of(15, 30), LocalTime.of(23, 30));

       schedule1.addMeeting(meeting1);
       schedule1.addMeeting(meeting2);

        System.out.println(schedule1.percentageSpentInMeetings());

        System.out.println(schedule1.scheduleAsString());
    }
}
