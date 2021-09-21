package com.dao;


import com.pojo.Employee;

public interface EmployeeDAO {
	public boolean insert(Employee emp);
	public boolean delete(String empId);
	public Employee search(String empId);
}
