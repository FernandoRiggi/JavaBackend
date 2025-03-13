package Aula04.Ex02;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

public class Schedule {

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

    private int counter = 0;

    private int max = 25;

    private Schedule(LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static Schedule of(LocalDate date, LocalTime startTime, LocalTime endTime){
        if(startTime.isAfter(endTime)) return null;
        return new Schedule(date, startTime, endTime);
    }

    private Meeting[] meetings = new Meeting[max];

    public void addMeeting(Meeting meeting) {
        if(meeting.getStartTime().isBefore(startTime) || meeting.getEndTime().isAfter(endTime)) {
            System.out.println("The meeting is outside of the schedule range");
            return;
        }
        if (counter >= max) {
            max*=2;
            Meeting[] temp = new Meeting[max];

            System.arraycopy(meetings, 0, temp, 0, counter);

            meetings = temp;
        }
        meetings[counter] = meeting;
        counter++;
    }

    public void removeMeeting(Meeting meeting) {
        for (int i = 0; i < counter; i++) {
            if (meetings[i].equals(meeting)) {
                for (int j = 0; j < counter-1; j++) {
                    meetings[j] = meetings[j+1];
                }
                meetings[counter-1] = null;
                counter--;
            }
        }
    }

    public double percentageSpentInMeetings() {
        long durationSchedule = Duration.between(startTime, endTime).toMinutes();
        long durationMeetings = 0;
        for (int i =0; i < counter; i++) {
            durationMeetings += Duration.between(meetings[i].getStartTime(), meetings[i].getEndTime()).toMinutes();
        }
        return ((double) durationMeetings/durationSchedule) * 100;
    }

    public String scheduleAsString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Schedule: " + date + "\n");
        sb.append("Start: " + startTime + "\n");
        sb.append("End: " + endTime + "\n");
        for(int i=0; i < counter; i++) {
            sb.append("Meeting[" + (i+1) + "]" + meetings[i].toString() + "\n");
        }
        String string = sb.toString();
        return string;
    }
}
