package skyPro.homework.services;

import skyPro.homework.models.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployees();

    void addEmployee(String firstName, String lastName, Integer department, int salary);

    void removeEmployee(String firstName, String lastName, Integer department, int salary);

    Employee findEmployee(String firstName, String lastName, Integer department, int salary);


}
