package skyPro.homework.services;

import skyPro.homework.models.Employee;

import java.util.List;

public interface ServiceInterface {
    List<Employee> returnEmployees();

    void addEmployee(String firstName, String lastName, int department, int salary);

    void removeEmployee(String firstName, String lastName, int department, int salary);

    Employee findEmployee(String firstName, String lastName, int department, int salary);

}
