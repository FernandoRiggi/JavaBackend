package Aula07.Ex03;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Appointment {
    private final String APPOINTMENTID;
    private Patient patient;
    private Doctor doctor;
    private LocalDateTime appointmentDate;
    private LocalDateTime appointmentTime;
    private String status;

    public Appointment(String APPOINTMENTID, Patient patient, Doctor doctor, LocalDateTime appointmentTime, LocalDateTime appointmentDate, String status) {
        this.APPOINTMENTID = APPOINTMENTID;
        this.patient = patient;
        this.doctor = doctor;
        this.appointmentTime = appointmentTime;
        this.appointmentDate = appointmentDate;
        this.status = status;
    }

    public String getAppointmentInfo() {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return String.format("""
            _______________________________________________________________
                    Appointment ID: %s,
                    Patient Name: %s,
                    Doctor Name: %s,
                    Specialization: %s
                    Date: %s,
                    Time: %s,
                    Status: %s
            _______________________________________________________________
            """, APPOINTMENTID, patient.getName(), doctor.getName(), doctor.getSpecialization(), formatter.format(appointmentDate), formatter.format(appointmentTime), status);
    }

    public void rescheduleAppointment(LocalDateTime appointmentDate, LocalDateTime appointmentTime) {
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
    }

    public void cancelAppointment() {
        this.status = "Canceled";
    }
}
