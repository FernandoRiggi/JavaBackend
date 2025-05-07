package Aula09.Ex02;

public class RegisterEmployeeService {
    private final Repository<String, Employee> repository;

    public RegisterEmployeeService(Repository<String, Employee> repository){
        this.repository = repository;
    }

    public void register(Employee employee){
        if(repository.getById((employee.getId()))==null){
            repository.save(employee);
        }else return;
    }
}
