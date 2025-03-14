package Aula04.Ex02;

import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {

       Meeting meeting1 =  Meeting.of("POO", LocalTime.of(20, 30), LocalTime.of(21, 30));
       Meeting meeting2 = Meeting.of("BD", LocalTime.of(14, 30), LocalTime.of(17, 30));
       System.out.println("The duration of the meeting is: " + meeting1.durationInMinutes() + " minutes");
       Meeting meeting3 = Meeting.of("DATASTRUCTURE", LocalTime.of(20,0), LocalTime.of(22, 0));
       Schedule schedule1 = Schedule.of(LocalDate.of(2020, 1, 1), LocalTime.of(10, 30), LocalTime.of(23, 30));

       schedule1.addMeeting(meeting1);
       schedule1.addMeeting(meeting2);
       schedule1.addMeeting(meeting3);
       System.out.println("The percentage of the day in meeting is: " + schedule1.percentageSpentInMeetings() + "%");

       System.out.println(schedule1.scheduleAsString());
       schedule1.removeMeeting(meeting1);
        System.out.println(schedule1.scheduleAsString());
    }
}
