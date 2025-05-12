package PracticalExercises.SecondPractical.Ex03;

public class Patient {
    private final String CPF;
    private String name;
    private String email;

    public Patient(String CPF, String name, String email) {
        this.CPF = CPF;
        this.name = name;
        this.email = email;
    }

    public String getPatientInfo(){
        return String.format("CPF: %s, name: %s, email: %s", CPF, name, email);
    }

    public String getCPF() {
        return CPF;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
