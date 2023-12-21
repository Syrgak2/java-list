package skyPro.homework.controlers;


import org.springframework.web.bind.annotation.*;

import skyPro.homework.models.Employee;
import skyPro.homework.services.impl.EmployeeServiceImpl;
import skyPro.homework.services.EmployeeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    public List< Employee> getEmployees() {
        return new ArrayList<>(employeeService.getEmployees());
    }

    @GetMapping("/add")
    public Employee add(@RequestParam("firstName") String firstName,
                        @RequestParam("lastName") String lastName,
                        @RequestParam("department") Integer department,
                        @RequestParam("salary") int salary) {
        return employeeService.addEmployee(firstName, lastName, department, salary);
    }

    @GetMapping("/remove")
    public Employee remove(@RequestParam("firstName") String firstName,
                         @RequestParam("lastName") String lastName) {
        return employeeService.removeEmployee(firstName, lastName);
    }

    @GetMapping("/find")
    public Employee find(@RequestParam("firstName") String firstName,
                         @RequestParam("lastName") String lastName){
        return employeeService.findEmployee(firstName, lastName);
    }


}
