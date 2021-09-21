package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.pojo.Employee;

public interface EmployeeDAO {
	public int insert(Employee emp);
	public int delete(String empId);
	public Employee search(String empId);
	public List<Employee> getAllEmployees();
	public int employeeCount();
	public String employeeName(String empId);
	public int updateName(String empId, String newName);
	
}
