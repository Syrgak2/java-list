package skyPro.homework.services;

import skyPro.homework.models.Employee;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DepartmentService {
    Optional<Employee> getMaxSalaryInDepartment(Integer department);

    Optional<Employee> getmMinSalaryInDepartment(Integer department);


    //    возвращает сумму зарплат по департаменту.
    int getSalarySumInDepartment(Integer departmentId);

    List<Employee> getEmployeeInDepartment(Integer department);

    Map<Integer, List<Employee>> getAllEmployeeByDepartment();
}
