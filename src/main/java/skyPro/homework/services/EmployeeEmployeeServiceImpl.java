package skyPro.homework.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import skyPro.homework.exceptions.EmployeeAlreadyAddedException;
import skyPro.homework.exceptions.EmployeeNotFoundException;
import skyPro.homework.exceptions.EmployeeStorageIsFullException;
import skyPro.homework.exceptions.InvalidInputArgumentsException;
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
        Employee requestedEmployee = collectEmployee(firstName, lastName, department, salary);

        if (employees.size() >= MAX_POSSIBLE_OF_EMPLOYEES) {
            throw new EmployeeStorageIsFullException();
        }

        if (employees.contains(requestedEmployee)){
            throw new EmployeeAlreadyAddedException();
        }

        employees.add(requestedEmployee);
    }

    @Override
    public String removeEmployee(String firstName, String lastName, Integer department, int salary) {
        Employee requestedEmployee = collectEmployee(firstName, lastName, department, salary);

        if (!employees.contains(requestedEmployee)) {
            throw new EmployeeNotFoundException();
        }

        employees.remove(requestedEmployee);

        return "Сотрудник с фио " + firstName + " " + lastName;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName, Integer department, int salary) {
        Employee requestedEmployee = collectEmployee(firstName, lastName, department, salary);

        if (!employees.contains(requestedEmployee)) {
            throw new EmployeeNotFoundException();
        }

        return requestedEmployee;
    }


//    collects employee from input data
//    returns an exception if the string contains invalid characters
    private Employee collectEmployee(String firstName, String lastName, Integer department, int salary) {
        if (!StringUtils.isAlpha(firstName) && StringUtils.isAlpha(lastName)) {
            throw new InvalidInputArgumentsException();
        }
        return new Employee(StringUtils.capitalize(firstName),
                StringUtils.capitalize(lastName),
                department,
                salary);
    }

}