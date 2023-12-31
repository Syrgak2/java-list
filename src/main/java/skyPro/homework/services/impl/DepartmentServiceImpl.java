package skyPro.homework.services.impl;

import org.springframework.stereotype.Service;
import skyPro.homework.models.Employee;
import skyPro.homework.services.DepartmentService;
import skyPro.homework.services.EmployeeService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //  возвращает максимальную зарплату по департаменту.
    @Override
    public Optional<Employee> getMaxSalaryInDepartment(Integer department) {
        return employeeService.getEmployees().stream()
                .filter(e -> e.getDepartment().equals(department))
                .max(Comparator.comparing(Employee::getSalary));
    }

    //    возвращает минимальную зарплату по департаменту.
    @Override
    public Optional<Employee> getmMinSalaryInDepartment(Integer department) {
        return employeeService.getEmployees().stream()
                .filter(e -> e.getDepartment().equals(department))
                .min(Comparator.comparing(Employee::getSalary));
    }

    //    возвращает сумму зарплат по департаменту.
    @Override
    public int getSalarySumInDepartment(Integer departmentId) {
        int sum = 0;
        for (Employee element : employeeService.getEmployees()) {
            if (Objects.equals(element.getDepartment(), departmentId)) {
                sum += element.getSalary();
            }
        }

        return sum;
    }

    //    возвращает список сотрудников по департаменту
    @Override
    public List<Employee> getEmployeeInDepartment(Integer departmentId) {
        return employeeService.getEmployees().stream()
                .filter(e -> e.getDepartment().equals(departmentId))
                .collect(Collectors.toList());
    }

    //    возвращает сотрудников, сгруппированых по отделам
    @Override
    public Map<Integer, List<Employee>> getAllEmployeeByDepartment() {
        return employeeService.getEmployees().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.mapping(e -> e,
                                Collectors.toList())));
    }
}
