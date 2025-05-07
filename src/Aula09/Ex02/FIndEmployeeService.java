package Aula09.Ex02;

public class FIndEmployeeService {
    private final Repository<String, Employee> repository;

    public FIndEmployeeService(Repository<String, Employee> repository) {
        this.repository = repository;
    }

    public Employee findByid(String id){
        return repository.getById(id);
    }
}
