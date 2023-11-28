package skyPro.homework.controlers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import skyPro.homework.models.Employee;
import skyPro.homework.services.DepartmentService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Optional<Employee> findMaxSalaryInDepartment(@RequestParam("departmentId") Integer departmentId) {
        return departmentService.maxSalaryInDepartment(departmentId);
    }

    @GetMapping("/min-salary")
    public Optional<Employee> findMinSalaryInDepartment(@RequestParam("departmentId") Integer departmentId) {
        return departmentService.minSalaryInDepartment(departmentId);
    }

    @GetMapping(value = "/all", params = "departmentId")
    public List<Employee> returnEmployeeInDepartment(@RequestParam("departmentId") Integer departmentId) {
        return departmentService.employeeInDepartment(departmentId);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> returnAllEmployeeByDepartment() {
        return departmentService.allEmployeeByDepartment();
    }
}
