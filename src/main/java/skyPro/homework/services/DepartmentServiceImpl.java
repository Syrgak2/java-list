package skyPro.homework.services;

import org.springframework.stereotype.Service;
import skyPro.homework.models.Employee;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Optional<Employee> maxSalaryInDepartment(Integer department) {
        return employeeService.getEmployees().stream()
                .filter(e -> e.getDepartment().equals(department))
                .max(Comparator.comparing(Employee::getSalary));
    }

    @Override
    public Optional<Employee> minSalaryInDepartment(Integer department) {
        return employeeService.getEmployees().stream()
                .filter(e -> e.getDepartment().equals(department))
                .min(Comparator.comparing(Employee::getSalary));
    }

    @Override
    public List<Employee> employeeInDepartment(Integer department) {
        return employeeService.getEmployees().stream()
                .filter(e -> e.getDepartment().equals(department))
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> allEmployeeByDepartment() {
        return employeeService.getEmployees().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.mapping(e -> e,
                                Collectors.toList())));
    }
}
