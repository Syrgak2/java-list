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

    private List<Employee> employees = new ArrayList<>(new ArrayList<>(employeeService.getEmployeesMap().values()));

    //  возвращает максимальную зарплату по департаменту.
    @Override
    public Optional<Employee> maxSalaryInDepartment(Integer department) {
        return employees.stream()
                .filter(e -> e.getDepartment().equals(department))
                .max(Comparator.comparing(Employee::getSalary));
    }

    //    возвращает минимальную зарплату по департаменту.
    @Override
    public Optional<Employee> minSalaryInDepartment(Integer department) {
        return employees.stream()
                .filter(e -> e.getDepartment().equals(department))
                .min(Comparator.comparing(Employee::getSalary));
    }

    //    возвращает сумму зарплат по департаменту.
    @Override
    public int getSalarySumInDepartment(Integer departmentId) {
        int sum = 0;
        for (Employee element : employees) {
            if (Objects.equals(element.getDepartment(), departmentId)) {
                sum += element.getSalary();
            }
        }

        return sum;
    }

    //    возвращает список сотрудников по департаменту
    @Override
    public List<Employee> employeeInDepartment(Integer departmentId) {
        return employees.stream()
                .filter(e -> e.getDepartment().equals(departmentId))
                .collect(Collectors.toList());
    }

    //    возвращает сотрудников, сгруппированых по отделам
    @Override
    public Map<Integer, List<Employee>> allEmployeeByDepartment() {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.mapping(e -> e,
                                Collectors.toList())));
    }
}
