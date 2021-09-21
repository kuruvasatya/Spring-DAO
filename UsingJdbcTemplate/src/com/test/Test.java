package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.EmployeeDAOImp;
import com.pojo.Employee;
import com.service.EmployeeService;

public class Test {

	public static void main(String[] args) {
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		EmployeeService e = (EmployeeService) ac.getBean("employeeService");
		
		//insert
		System.out.println("inserted employee : "  + e.insertEmployee(new Employee("xyz5","neha",45000)));
		
		//count 
		System.out.println("Number of employee are..." + e.getCount());
	
		//print all employees
		System.out.println("employee are ...");
		for(Employee emp: e.getEmployees()) {
			System.out.println(emp);
		}
		
		//delete 
		System.out.println("delete employee : " + e.removeEmployee("xyz4"));
		System.out.println("number of employees are : " + e.getCount());
		
		//search
		System.out.println("Searching for xyz1..." + e.search("xyz1"));
		
		//employee name
		System.out.println("employee name of xyz1 :" + e.getEmployeeName("xyz1"));
		
		//update
		System.out.println("update name of xyz1: " + e.changeName("xyz1", "ragini"));
		
	}

}
