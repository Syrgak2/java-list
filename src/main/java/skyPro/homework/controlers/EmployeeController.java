package skyPro.homework.controlers;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import skyPro.homework.exceptions.EmployeeAlreadyAddedException;
import skyPro.homework.exceptions.EmployeeNotFoundException;
import skyPro.homework.exceptions.EmployeeStorageIsFullException;
import skyPro.homework.models.Employee;
import skyPro.homework.services.EmployeeServiceImpl;
import skyPro.homework.services.ServiceInterface;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final ServiceInterface employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    public List<Employee> printEmployees() {
        return employeeService.returnEmployees();
    }

    @GetMapping("/add")
    @ExceptionHandler({EmployeeAlreadyAddedException.class, EmployeeStorageIsFullException.class})
    public String add(@RequestParam("firstName") String firstName,
                        @RequestParam("lastName") String lastName,
                        @RequestParam("department") Integer department,
                        @RequestParam("salary") int salary) {
        employeeService.addEmployee(firstName, lastName, department, salary);
//        return employeeService.findEmployee(firstName, lastName, department, salary);
        return "Employee added";
    }

    @GetMapping("/remove")
    @ExceptionHandler({EmployeeNotFoundException.class})
    public String remove(@RequestParam("firstName") String firstName,
                         @RequestParam("lastName") String lastName,
                         @RequestParam("department") Integer department,
                         @RequestParam("salary") int salary) {
        employeeService.removeEmployee(firstName, lastName, department, salary);
        return "Сотрудник с фио " + firstName + " " + lastName;
    }

    @GetMapping("/find")
    @ExceptionHandler({EmployeeNotFoundException.class})
    public Employee find(@RequestParam("firstName") String firstName,
                         @RequestParam("lastName") String lastName,
                         @RequestParam("department") Integer department,
                         @RequestParam("salary") int salary) {
        return employeeService.findEmployee(firstName, lastName, department, salary);
    }

    @GetMapping("/departments/max-salary")
    public Optional<Employee> findMaxSalaryInDepartment(@RequestParam("departmentId") Integer departmentId) {
        return employeeService.MaxSalaryInDepartment(departmentId);
    }

    @GetMapping("/departments/min-salary")
    public Optional<Employee> findMinSalaryInDepartment(@RequestParam("departmentId") Integer departmentId) {
        return employeeService.MinSalaryInDepartment(departmentId);
    }

    @GetMapping("/departments/all")
    public List<Employee> returnEmployeeInDepartment(@RequestParam("departmentId") Integer departmentId) {
        return employeeService.EmployeeInDepartment(departmentId);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> returnAllEmployeeByDepartment() {
        return employeeService.AllEmployeeByDepartment();
    }


}
