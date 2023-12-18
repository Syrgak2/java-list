package skyPro.homework.controlers;


import org.springframework.web.bind.annotation.*;

import skyPro.homework.models.Employee;
import skyPro.homework.services.impl.EmployeeEmployeeServiceImpl;
import skyPro.homework.services.EmployeeService;

import java.util.List;


@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeEmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    public List<Employee> printEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("/add")
    public String add(@RequestParam("firstName") String firstName,
                        @RequestParam("lastName") String lastName,
                        @RequestParam("department") Integer department,
                        @RequestParam("salary") int salary) {
        employeeService.addEmployee(firstName, lastName, department, salary);
        return "Employee added";
    }

    @GetMapping("/remove")
    public String remove(@RequestParam("firstName") String firstName,
                         @RequestParam("lastName") String lastName,
                         @RequestParam("department") Integer department,
                         @RequestParam("salary") int salary) {
        return employeeService.removeEmployee(firstName, lastName, department, salary);
    }

    @GetMapping("/find")
    public Employee find(@RequestParam("firstName") String firstName,
                         @RequestParam("lastName") String lastName,
                         @RequestParam("department") Integer department,
                         @RequestParam("salary") int salary){
        return employeeService.findEmployee(firstName, lastName, department, salary);
    }


}
