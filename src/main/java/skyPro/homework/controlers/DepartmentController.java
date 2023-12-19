package skyPro.homework.controlers;

import org.springframework.web.bind.annotation.*;
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

//    возвращает максимальную зарплату по департаменту.
    @GetMapping("/{id}/salary/max")
    public Optional<Employee> findMaxSalaryInDepartment(@PathVariable("id") Integer departmentId) {
        return departmentService.maxSalaryInDepartment(departmentId);
    }

//    возвращает минимальную зарплату по департаменту.
    @GetMapping("{id}/salary/min")
    public Optional<Employee> findMinSalaryInDepartment(@PathVariable("id") Integer departmentId) {
        return departmentService.minSalaryInDepartment(departmentId);
    }

    //    возвращает сумму зарплат по департаменту.
    @GetMapping("/{id}/salary/sum")
    public int returnSalarySumInDepartment(@PathVariable("id") Integer departmentId) {
        return departmentService.getSalarySumInDepartment(departmentId);
    }


//     возвращает список сотрудников по департаменту.
    @GetMapping(value = "/{id}/employees")
    @ResponseBody
    public List<Employee> returnEmployeeInDepartment(@PathVariable("id") Integer departmentId) {
        return departmentService.employeeInDepartment(departmentId);
    }

//    Возвращает сотрудников, сгруппированых по отделам
    @GetMapping("/employees")
    public Map<Integer, List<Employee>> returnAllEmployeeByDepartment() {
        return departmentService.allEmployeeByDepartment();
    }
}
