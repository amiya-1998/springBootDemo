package com.viva.springBootDemo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
	@Query("SELECT e FROM Employee e WHERE e.name=:name")
	public List<Employee> findByName(@Param("name") String name);
	
	@Query("SELECT new map(e.dept as dept, COUNT(e) as numberOfEmployees) FROM Employee e GROUP BY e.dept")
	List<?> getNumberOfEmployeesInEveryDepartment();
}
