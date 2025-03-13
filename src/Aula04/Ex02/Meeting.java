package Aula04.Ex02;

import java.time.Duration;
import java.time.LocalTime;

public class Meeting {

    private String description;

    private LocalTime startTime;

    private LocalTime endTime;

    public LocalTime getEndTime() {
        return endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    private Meeting(String description, LocalTime startTime, LocalTime endTime) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static Meeting of(String description, LocalTime startTime, LocalTime endTime) {
        if(startTime.isAfter(endTime)) return null;
        return new Meeting(description, startTime, endTime);
    }

    public long durationInMinutes(){
        return Duration.between(startTime, endTime).toMinutes();
    }

    @Override
    public String toString() {
        return "[description=" + description + ", startTime=" + startTime + ", endTime=" + endTime + "]";
    }
}
