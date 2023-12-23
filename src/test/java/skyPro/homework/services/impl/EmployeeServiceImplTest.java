package skyPro.homework.services.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import skyPro.homework.exceptions.EmployeeAlreadyAddedException;
import skyPro.homework.exceptions.EmployeeNotFoundException;
import skyPro.homework.exceptions.InvalidInputArgumentsException;
import skyPro.homework.models.Employee;

import java.util.*;

public class EmployeeServiceImplTest {

    private String employeeFirstName;
    private String employeeLastName;
    private Integer employeeDepartment;
    private int employeeSalary;

    private EmployeeServiceImpl employeeService;

    @BeforeEach
    public void setUp() {
        employeeService = new EmployeeServiceImpl();

        employeeFirstName = "Syrgak";
        employeeLastName = "Karybekov";
        employeeDepartment = 1;
        employeeSalary = 1200;
    }

    @Test
    public void addEmployeeTesting() {

//        given
        Employee excepted = new Employee(employeeFirstName, employeeLastName, employeeDepartment, employeeSalary);

//        When
        Employee actual = employeeService.addEmployee(employeeFirstName, employeeLastName, employeeDepartment, employeeSalary);

//        Then
        Assertions.assertEquals(excepted, actual);
    }

    @Test
    public void TestingAddAlreadyAddedEmployee() {
//        Given
        employeeService.addEmployee(employeeFirstName, employeeLastName, employeeDepartment, employeeSalary);

//        Then
        Assertions.assertThrows(
                EmployeeAlreadyAddedException.class,
                () -> employeeService.addEmployee(employeeFirstName, employeeLastName, employeeDepartment, employeeSalary)
        );
    }


    @Test
    public void removeEmployeeTesting() {
//        given
        employeeService.addEmployee(employeeFirstName, employeeLastName, employeeDepartment, employeeSalary);
        Employee expected = new Employee(employeeFirstName, employeeLastName, employeeDepartment, employeeSalary);

//       When
        Employee actual = employeeService.removeEmployee(employeeFirstName, employeeLastName);

//        Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findEmployeeTesting() {
//        Given
        Employee expected = employeeService.addEmployee(employeeFirstName, employeeLastName, employeeDepartment, employeeSalary);

//        When
        Employee actual = employeeService.findEmployee(employeeFirstName, employeeLastName);

//      Then
        Assertions.assertEquals(expected, actual);
    }

//    Обязательно к чтению!!!
//    Тест выброса исключение EmployeeInvalidInput
//    Метод тестирует выброс исключение во всех возможных местах класса EmployeeServiceImpl
//    Можно ли так делеть? Если можно ответьте коментарием!!!
    @Test
    public void TestingWhenInvalidInputArguments() {
//        Given
        String wrongFirstName = "Wrong1";
        String wrongLastName = "Wrong2";

//        Then
        Assertions.assertThrows(
                InvalidInputArgumentsException.class,
                () -> employeeService.addEmployee(wrongFirstName, wrongLastName, 1, 100)
        );

        Assertions.assertThrows(
                InvalidInputArgumentsException.class,
                () -> employeeService.removeEmployee(wrongFirstName, wrongLastName)
        );


        Assertions.assertThrows(
                InvalidInputArgumentsException.class,
                () -> employeeService.findEmployee(wrongFirstName, wrongLastName)
        );

    }

//    Тест выброса исключение EmployeeNotFoundException
    @Test
    public void TestingWhenEmployeeNotFound() {
//        Given
        String wrongFirstName = "Wrong";
        String wrongLastName = "Name";

        employeeService.addEmployee(employeeFirstName, employeeLastName, employeeDepartment, employeeSalary);

        Assertions.assertThrows(
                EmployeeNotFoundException.class,
                () -> employeeService.removeEmployee(wrongFirstName, wrongLastName)
        );

        Assertions.assertThrows(
                EmployeeNotFoundException.class,
                () -> employeeService.findEmployee(wrongFirstName, wrongLastName)
        );
    }

    @Test
    public void getAllEmployee() {

         Employee employee1 = new Employee("A", "A", 1, 1200);
         Employee employee2 = new Employee("B", "B", 1, 1300);
         Employee employee3 = new Employee("C", "C", 2, 1250);

//        given
        List<Employee> expected = new ArrayList<>();
        expected.add(employee1);
        expected.add(employee2);
        expected.add(employee3);

//        when
        employeeService.addEmployee(employee1.getFirstName(),
                employee1.getLastName(),
                employee1.getDepartment(),
                employee1.getSalary());

        employeeService.addEmployee(employee2.getFirstName(),
                employee2.getLastName(),
                employee2.getDepartment(),
                employee2.getSalary());

        employeeService.addEmployee(employee3.getFirstName(),
                employee3.getLastName(),
                employee3.getDepartment(),
                employee3.getSalary());

        List<Employee> actual = employeeService.getEmployees();

//        Then
        Assertions.assertEquals(expected, actual);
    }
}
