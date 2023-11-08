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
    public Employee add(@RequestParam("firstName") String firstName,
                        @RequestParam("lastName") String lastName) {
        employeeService.addEmployee(firstName, lastName);
        return employeeService.findEmployee(firstName, lastName);
    }

    @GetMapping("/remove")
    @ExceptionHandler({EmployeeNotFoundException.class})
    public String remove(@RequestParam("firstName") String firstName,
                           @RequestParam("lastName") String lastName) {
        employeeService.removeEmployee(firstName, lastName);
        return "Сотрудник с фио " + firstName + " " + lastName;
    }

    @GetMapping("/find")
    @ExceptionHandler({EmployeeNotFoundException.class})
    public Employee find(@RequestParam("firstName") String firstName,
                         @RequestParam("lastName") String lastName) {
        return employeeService.findEmployee(firstName, lastName);
    }
}
