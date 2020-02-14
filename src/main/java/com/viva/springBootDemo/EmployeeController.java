package com.viva.springBootDemo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class EmployeeController {
	@Autowired
	EmployeeRepo empRepo;
	
//	Test API
//	@RequestMapping(path = "/test", method = RequestMethod.GET)
	@GetMapping("/test")
	public String test() {
		return "Backend Server Working!!";
	}
	
//	Get All Employees
//	@RequestMapping(path = "/employees", method = RequestMethod.GET)
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return empRepo.findAll();
	}
	
//	Add Employee
//	@RequestMapping(path = "/employees", method = RequestMethod.POST)
	@PostMapping("/employees")
	public void addEmployee(@RequestParam Map<String, String> body) {
		Employee employee = new Employee(body.get("name"), Double.parseDouble(body.get("salary")), body.get("dept"));
		empRepo.save(employee);
	}
	
//	Fetch Employee with a particular ID
	@GetMapping("/employees/{id}")
	public Employee getEmployeeById(@PathVariable("id") int id) {
		Employee employee = empRepo.findById(id).get();
		return employee;
	}
	
//	Get Employee by Name
	@GetMapping("/employees/name/{name}")
	public List<Employee> getEmployeeByName(@PathVariable("name") String name) {
		return empRepo.findByName(name);
	}
	
//	Delete Employee with a particular ID
	@DeleteMapping("/employees/{id}")
	public void deleteEmployeeById(@PathVariable("id") int id) {
		empRepo.deleteById(id);
	}
	
//	Update an Employee Record
	@PostMapping("/employees/{id}")
	public void updateEmployee(@PathVariable("id") int id, @RequestParam Map<String, String> body) {
		Employee employee = getEmployeeById(id);
		employee.name = body.get("name");
		employee.salary = Double.parseDouble(body.get("salary"));
		employee.dept = body.get("dept");
		empRepo.save(employee);
	}
	
//	Get Number of Employees in Every Department
	@GetMapping("/numberOfEmployeesInDept")
	public List<?> getNumberOfEmployeesInEveryDepartment() {
		return empRepo.getNumberOfEmployeesInEveryDepartment();
	}
	
}
