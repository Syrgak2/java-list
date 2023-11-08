package skyPro.homework.services;

import org.springframework.stereotype.Service;

import skyPro.homework.exceptions.EmployeeAlreadyAddedException;
import skyPro.homework.exceptions.EmployeeNotFoundException;
import skyPro.homework.exceptions.EmployeeStorageIsFullException;
import skyPro.homework.models.Employee;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    public static final int MAX_POSSIBLE_OF_EMPLOYEES = 10;

    List<Employee> employees = new ArrayList<>(List.of(
            new Employee("Jak", "David"),
            new Employee("Luna", "Sun")
    ));

    public List<Employee> returnEmployees() {
        return employees;
    }

    public void addEmployee(String firstName, String lastName) {
        Employee employee = searchEmployee(firstName, lastName);

        if (employees.size() >= MAX_POSSIBLE_OF_EMPLOYEES) {
            throw new EmployeeStorageIsFullException();
        } else if (employee != null) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.add(new Employee(firstName,lastName));
    }

    public void removeEmployee(String firstName, String lastName) {
        Employee employee = searchEmployee(firstName, lastName);

        if (employee == null) {
            throw new EmployeeNotFoundException();
        } else {
            employees.remove(employee);
        }
    }

    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = searchEmployee(firstName, lastName);
        if (employee == null) {
            throw new EmployeeNotFoundException();
        } else {
            return employee;
        }

    }

    private Employee searchEmployee(String firstName, String lastName) {
        return employees
                .stream()
                .filter(searchedEmployee -> (searchedEmployee.getFirstName().equals(firstName)
                        && searchedEmployee.getLastName().equals(lastName)))
                .findFirst()
                .orElse(null);
    }

}
