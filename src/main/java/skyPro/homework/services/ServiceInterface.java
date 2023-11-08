package skyPro.homework.services;

import skyPro.homework.models.Employee;

import java.util.List;

public interface ServiceInterface {
    List<Employee> returnEmployees();

    void addEmployee(String firstName, String lastName);

    void removeEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);
}