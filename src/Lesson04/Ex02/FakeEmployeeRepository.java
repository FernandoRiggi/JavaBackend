package Lesson04.Ex02;


public class FakeEmployeeRepository implements Repository{
    private Employee[] employees;
    private int index;

    public FakeEmployeeRepository(int capacity) {
        this.employees = new Employee[capacity];
        this.index = 0;
    }

    @Override
    public void save(Object entity) {
        employees[index++] = (Employee) entity;
    }

    @Override
    public Object getById(Object id) {
        String employeeId = (String) id;
        for(int i =0; i<index; i++){
            if(employees[i].getId().equals(employeeId)) return employees[i];
        }

        return  null;
    }
}
