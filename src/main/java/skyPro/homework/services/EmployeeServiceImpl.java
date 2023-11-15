package skyPro.homework.services;

import org.springframework.stereotype.Service;

import skyPro.homework.exceptions.EmployeeAlreadyAddedException;
import skyPro.homework.exceptions.EmployeeNotFoundException;
import skyPro.homework.exceptions.EmployeeStorageIsFullException;
import skyPro.homework.models.Employee;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class EmployeeServiceImpl implements ServiceInterface{
    public static final int MAX_POSSIBLE_OF_EMPLOYEES = 5;

    List<Employee> employees = new ArrayList<>();

    @Override
    public List<Employee> returnEmployees() {
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

    @Override
    public Optional<Employee> MaxSalaryInDepartment(Integer department) {
        return employees.stream()
                .filter(e -> e.getDepartment().equals(department))
                .max(Comparator.comparing(Employee::getSalary));
    }

    @Override
    public Optional<Employee> MinSalaryInDepartment(Integer department) {
        return employees.stream()
                .filter(e -> e.getDepartment().equals(department))
                .min(Comparator.comparing(Employee::getSalary));
    }

    @Override
    public List<Employee> EmployeeInDepartment(Integer department) {
        return employees.stream()
                .filter(e -> e.getDepartment().equals(department))
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> AllEmployeeByDepartment() {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.mapping(e -> e,
                        Collectors.toList())));
    }




}