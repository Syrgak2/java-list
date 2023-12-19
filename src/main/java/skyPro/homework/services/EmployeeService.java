package skyPro.homework.services;

import skyPro.homework.models.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    Map<String, Employee> getEmployeesMap();

    void addEmployee(String firstName, String lastName, Integer department, int salary);

    Employee removeEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);


}
