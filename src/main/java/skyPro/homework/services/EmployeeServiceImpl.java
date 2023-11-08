package skyPro.homework.services;

import org.springframework.stereotype.Service;

import skyPro.homework.exceptions.EmployeeAlreadyAddedException;
import skyPro.homework.exceptions.EmployeeNotFoundException;
import skyPro.homework.exceptions.EmployeeStorageIsFullException;
import skyPro.homework.models.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class EmployeeServiceImpl implements ServiceInterface{
    public static final int MAX_POSSIBLE_OF_EMPLOYEES = 5;

    Map<String, Employee> employees = new HashMap<>();

    @Override
    public List<Employee> returnEmployees() {
        return new ArrayList<>(employees.values());
    }

    @Override
    public void addEmployee(String firstName, String lastName) {

        if (employees.size() >= MAX_POSSIBLE_OF_EMPLOYEES) {
            throw new EmployeeStorageIsFullException();
        }

        employees.put(firstName + lastName, new Employee(firstName,lastName));
    }

    @Override
    public void removeEmployee(String firstName, String lastName) {

        Employee employee = employees.get(firstName + lastName);

        if (employee == null) {
            throw new EmployeeNotFoundException();
        }

        employees.remove(firstName + lastName);
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee requestedEmployee = new Employee(firstName, lastName);

        Employee employee = employees.get(firstName + lastName);

        if (employee == null) {
            throw new EmployeeNotFoundException();
        }
        return employee;



    }
}