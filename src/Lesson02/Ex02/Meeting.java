package Lesson02.Ex02;

import java.time.Duration;
import java.time.LocalTime;

public class Meeting {

    private final String description;
    private final LocalTime startTime;
    private final LocalTime endTime;

    private Meeting(String description, LocalTime startTime, LocalTime endTime) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static Meeting of(String description, LocalTime startTime, LocalTime endTime) {
        if (!startTime.isBefore(endTime)) {
            throw new IllegalArgumentException("Start time must be before end time.");
        }
        return new Meeting(description, startTime, endTime);
    }

    public long durationInMinutes(){
        return Duration.between(startTime, endTime).toMinutes();
    }

    @Override
    public String toString() {
        return String.format("Meeting { description='%s', startTime=%s, endTime=%s }",
                description, startTime, endTime);
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }
}
