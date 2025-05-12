package PracticalExercises.SecondPractical.Ex03;

public class Doctor{
    private final String DOCTORID;
    private String name;
    private String specialization;

    public Doctor(String doctorId, String specialization) {
        this.DOCTORID = doctorId;
    }

    public String doctorInfo(){
        return String.format("DoctorID: %s, name: %s, specialization: %s", DOCTORID, name, specialization);
    }


    public String getDoctorId() {
        return DOCTORID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
