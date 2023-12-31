package skyPro.homework.services.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import skyPro.homework.exceptions.EmployeeAlreadyAddedException;
import skyPro.homework.exceptions.EmployeeNotFoundException;
import skyPro.homework.exceptions.EmployeeStorageIsFullException;
import skyPro.homework.exceptions.InvalidInputArgumentsException;
import skyPro.homework.models.Employee;
import skyPro.homework.services.EmployeeService;

import java.util.*;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    public static final int MAX_POSSIBLE_OF_EMPLOYEES = 5;

    Map<String, Employee> employees = new HashMap<>();

    @Override
    public List<Employee> getEmployees() {
        return new ArrayList<>(employees.values());
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, Integer department, int salary) {

        if (validateInput(firstName, lastName)) {
            throw new InvalidInputArgumentsException();
        }

        Employee requestedEmployee = new Employee(StringUtils.capitalize(firstName),
                StringUtils.capitalize(lastName),
                department,
                salary);

        if (employees.size() >= MAX_POSSIBLE_OF_EMPLOYEES) {
            throw new EmployeeStorageIsFullException();
        }

        if (employees.containsKey(firstName + lastName)){
            throw new EmployeeAlreadyAddedException();
        }

        employees.put(firstName + lastName, requestedEmployee);
        return employees.get(firstName + lastName);
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        if (validateInput(firstName, lastName)) {
            throw new InvalidInputArgumentsException();
        }

        if (!employees.containsKey(firstName + lastName)) {
            throw new EmployeeNotFoundException();
        }

        Employee employee = employees.get(firstName + lastName);

        employees.remove(firstName + lastName);

        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {

        if (validateInput(firstName, lastName)) {
            throw new InvalidInputArgumentsException();
        }

        if (!employees.containsKey(firstName + lastName)) {
            throw new EmployeeNotFoundException();
        }

        return employees.get(firstName + lastName);
    }

    private boolean validateInput(String firstName, String lastName) {
        return !StringUtils.isAlpha(firstName) || !StringUtils.isAlpha(lastName);
    }

}