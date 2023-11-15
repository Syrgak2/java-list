package skyPro.homework.services;

import skyPro.homework.models.Employee;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ServiceInterface {
    List<Employee> returnEmployees();

    void addEmployee(String firstName, String lastName, Integer department, int salary);

    void removeEmployee(String firstName, String lastName, Integer department, int salary);

    Employee findEmployee(String firstName, String lastName, Integer department, int salary);

    Optional<Employee> MaxSalaryInDepartment(Integer department);

    Optional<Employee> MinSalaryInDepartment(Integer department);


    List<Employee> EmployeeInDepartment(Integer department);

    Map<Integer, List<Employee>> AllEmployeeByDepartment();
}
