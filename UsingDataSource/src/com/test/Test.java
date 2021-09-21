package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.service.employeeServices;

public class Test {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		employeeServices es = ac.getBean("employeeServices",employeeServices.class);
		//es.insertEmployee("xyz1","satya",58000);
		//es.insertEmployee("xyz2", "ganesh", 750000);
		es.findEmployee("xyz1");
	}
}
