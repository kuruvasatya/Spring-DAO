package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.EmployeeDAOImp;
import com.pojo.Employee;

@Service
public class EmployeeService {
	@Autowired
	EmployeeDAOImp service;
	
	//insert
	public int insertEmployee(Employee e)
	{
		return service.insert(e);
	}
	
	//delete
	public int removeEmployee(String empId) {
		return service.delete(empId);
	}
	
	//search for employee
	public Employee search(String empId)
	{
		return service.search(empId);
	}
	
	//get all employees
	public List<Employee> getEmployees(){
		return service.getAllEmployees();
	}
	
	//get employee count
	public int getCount()
	{
		return service.employeeCount();
	}
	
	//get emoloyee name
	public String getEmployeeName(String empId)
	{
		return service.employeeName(empId);
	}
	
	//update
	public int changeName(String empId, String newName)
	{
		return service.updateName(empId, newName);
	}
	

}
