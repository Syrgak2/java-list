package skyPro.homework.services;

import skyPro.homework.models.Employee;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DepartmentService {
    Optional<Employee> maxSalaryInDepartment(Integer department);

    Optional<Employee> minSalaryInDepartment(Integer department);


    //    возвращает сумму зарплат по департаменту.
    int getSalarySumInDepartment(Integer departmentId);

    List<Employee> employeeInDepartment(Integer department);

    Map<Integer, List<Employee>> allEmployeeByDepartment();
}
