package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.dao.EmployeeDAOImp;
import com.pojo.Employee;

@Service
public class employeeServices {
	
	@Autowired
	EmployeeDAOImp empService;
	
	public void insertEmployee(String id, String name, double amt)
	{
		Boolean result = empService.insert(new Employee(id, name, amt));
		if(result)
			System.out.println("Insertion Succeessful");
		else
			System.out.println("insertion failed.....");
	}
	
	public void deleteEmployee(String empId)
	{
		Boolean result = empService.delete(empId);
		if(result)
			System.out.println("Deletion successful");
		else
			System.out.println("Deletion failed");
	}
	
	public void findEmployee(String empId)
	{
		Employee e = empService.search(empId);
		if(e == null)
			System.out.println("Employee not found...");
		else
			System.out.println(e);
			
	}

}
