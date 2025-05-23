package Lesson02.Ex02;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Schedule {

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private int counter = 0;
    private int max = 25;
    private Meeting[] meetings = new Meeting[max];

    private Schedule(LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static Schedule of(LocalDate date, LocalTime startTime, LocalTime endTime) {
        if (!startTime.isBefore(endTime)) {
            throw new IllegalArgumentException("Start time must be before end time.");
        }
        return new Schedule(date, startTime, endTime);
    }

    public void addMeeting(Meeting meeting) {
        if (isMeetingEndingAfterWorkTime(meeting)) return;
        if (isMeetingStartingBeforeWorkTime(meeting)) return;
        if (isMeetingOverlapping(meeting)) return;

        if (counter >= max) {
            increaseArraySize();
        }

        meetings[counter] = meeting;
        counter++;
    }

    private boolean isMeetingOverlapping(Meeting meeting) {
        for (int i = 0; i < counter; i++) {
            if (!((meeting.getEndTime()).isBefore(meetings[i].getStartTime()) || meeting.getStartTime().isAfter(meetings[i].getEndTime()))) {
                return true;
            }
        }
        return false;
    }

    private void increaseArraySize() {
        max *= 2;
        Meeting[] temp = new Meeting[max];

        System.arraycopy(meetings, 0, temp, 0, counter);

        meetings = temp;
    }

    private static boolean isMeetingStartingBeforeWorkTime(Meeting meeting) {
        return meeting.getStartTime().equals(meeting.getEndTime()) || meeting.getStartTime().isAfter(meeting.getEndTime());
    }

    private boolean isMeetingEndingAfterWorkTime(Meeting meeting) {
        return meeting.getStartTime().isBefore(startTime) || meeting.getEndTime().isAfter(endTime);
    }

    public void removeMeeting(Meeting meeting) {
        for (int i = 0; i < counter; i++) {
            if (meetings[i].equals(meeting)) {
                for (int j = i; j < counter - 1; j++) {
                    meetings[j] = meetings[j + 1];
                }
                meetings[--counter] = null;
                return;
            }
        }
    }

    public double percentageSpentInMeetings() {
        long durationSchedule = Duration.between(startTime, endTime).toMinutes();
        long durationMeetings = 0;
        for (int i = 0; i < counter; i++) {
            durationMeetings += Duration.between(meetings[i].getStartTime(), meetings[i].getEndTime()).toMinutes();
        }
        double percentage = ((double) durationMeetings / durationSchedule) * 100;
        return Double.parseDouble(String.format(Locale.US, "%.2f", percentage));
    }

    public String scheduleAsString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Schedule: " + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n");
        sb.append("Start: " + startTime + "\n");
        sb.append("End: " + endTime + "\n");
        for (int i = 0; i < counter; i++) {
            sb.append("Meeting[" + (i + 1) + "]" + meetings[i].toString() + "\n");
        }
        return sb.toString();
    }
}
