package skyPro.homework.services;

import skyPro.homework.models.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployees();

    Employee addEmployee(String firstName, String lastName, Integer department, int salary);

    Employee removeEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);


}
