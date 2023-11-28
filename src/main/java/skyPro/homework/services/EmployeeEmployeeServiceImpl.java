package skyPro.homework.services;

import org.springframework.stereotype.Service;

import skyPro.homework.exceptions.EmployeeAlreadyAddedException;
import skyPro.homework.exceptions.EmployeeNotFoundException;
import skyPro.homework.exceptions.EmployeeStorageIsFullException;
import skyPro.homework.models.Employee;

import java.util.*;


@Service
public class EmployeeEmployeeServiceImpl implements EmployeeService {
    public static final int MAX_POSSIBLE_OF_EMPLOYEES = 5;

    List<Employee> employees = new ArrayList<>();

    @Override
    public List<Employee> getEmployees() {
        return new ArrayList<>(employees);
    }

    @Override
    public void addEmployee(String firstName, String lastName, Integer department, int salary) {
        Employee requestedEmployee = new Employee(firstName, lastName, department, salary);

        if (employees.size() >= MAX_POSSIBLE_OF_EMPLOYEES) {
            throw new EmployeeStorageIsFullException();
        }


        if (employees.contains(requestedEmployee)){
            throw new EmployeeAlreadyAddedException();
        }

        employees.add(requestedEmployee);
    }

    @Override
    public void removeEmployee(String firstName, String lastName, Integer department, int salary) {
        Employee requestedEmployee = new Employee(firstName, lastName, department, salary);

        if (!employees.contains(requestedEmployee)) {
            throw new EmployeeNotFoundException();
        }

        employees.remove(requestedEmployee);
    }

    @Override
    public Employee findEmployee(String firstName, String lastName, Integer department, int salary) {
        Employee requestedEmployee = new Employee(firstName, lastName, department, salary);

        if (!employees.contains(requestedEmployee)) {
            throw new EmployeeNotFoundException();
        }

        throw new EmployeeNotFoundException();
    }

}