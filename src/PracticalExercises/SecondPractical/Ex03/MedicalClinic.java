package PracticalExercises.SecondPractical.Ex03;

public class MedicalClinic {
    private Patient[] patients;
    private Doctor[] doctors;
    private Appointment[] appointments;
    private int countPatient;
    private int countDoctor;
    private int countAppointment;

    public MedicalClinic(Patient[] patients, Appointment[] appointments, Doctor[] doctors) {
        this.patients = patients;
        this.appointments = appointments;
        this.doctors = doctors;
        patients = new Patient[100];
        doctors = new Doctor[100];
        appointments = new Appointment[100];
        countPatient = 0;
        countDoctor = 0;
        countAppointment = 0;
    }

    public Patient addPatient(Patient patient) {
        if(patient == null) return null;
        if(patients!=null){
            patients[countPatient] = patient;
            countPatient++;
            return patient;
        }
        return null;
    }

    public Doctor addDoctor(Doctor doctor) {
        if(doctor == null) return null;
        if(doctors!=null){
            doctors[countDoctor] = doctor;
            countDoctor++;
            return doctor;
        }
        return null;
    }

    public Appointment addAppointment(Appointment appointment) {
        if(appointment== null) return null;
        if(appointments!=null){
            appointments[countAppointment] = appointment;
            countAppointment++;
            return appointment;
        }
        return null;
    }


}
