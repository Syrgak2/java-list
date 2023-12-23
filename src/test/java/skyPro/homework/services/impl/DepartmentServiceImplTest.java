package skyPro.homework.services.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import skyPro.homework.models.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {
    private DepartmentServiceImpl departmentService;

    private List<Employee> employeesListForTesting;

    private Employee employeeMaxSalaryDepartment_1 = new Employee("Syrgak", "Karybekov", 1, 1500);
    private Employee employeeMinSalaryDepartment_1 = new Employee("Ivan", "Ivanov", 1, 100);

    @Mock
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    public void setUp() {
        departmentService = new DepartmentServiceImpl(employeeService);
        employeesListForTesting = new ArrayList<>();

        employeesListForTesting.add(employeeMaxSalaryDepartment_1);
        employeesListForTesting.add(employeeMinSalaryDepartment_1);
        employeesListForTesting.add(new Employee("Sergei", "Alexsandr", 2, 1400));
        employeesListForTesting.add(new Employee("Roma", "Ivanko", 3, 1350));

        Mockito.when(employeeService.getEmployees()).thenReturn(employeesListForTesting);
    }

    @Test
    public void EmployeeMaxSalaryInDepartment() {
//        Given
        Optional<Employee> expected = Optional.ofNullable(employeeMaxSalaryDepartment_1);

//        when
        Optional<Employee> actual = departmentService.getMaxSalaryInDepartment(1);

//        Then
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void EmployeeMinSalaryInDepartment() {
//        Given
        Optional<Employee> expected = Optional.ofNullable(employeeMinSalaryDepartment_1);

//        When
        Optional<Employee> actual = departmentService.getmMinSalaryInDepartment(1);
    }

    @Test
    public void getSalarySumInDepartment() {
//        Given
        int expected = employeeMaxSalaryDepartment_1.getSalary() + employeeMinSalaryDepartment_1.getSalary();

//        When
        int actual = departmentService.getSalarySumInDepartment(1);

//        Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getEmployeeInDepartment() {
//        Given
        List<Employee> expected = employeesListForTesting.stream()
                .filter(e -> e.getDepartment().equals(1))
                .collect(Collectors.toList());

//        When
        List<Employee> actual = departmentService.getEmployeeInDepartment(1);

//        Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getAllEmployeesByDepartment() {
//        Given
        Map<Integer, List<Employee>> expected = employeesListForTesting.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.mapping(e -> e,
                                Collectors.toList())));

//        When
        Map<Integer, List<Employee>> actual = departmentService.getAllEmployeeByDepartment();
    }


}
