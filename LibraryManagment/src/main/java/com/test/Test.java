package com.test;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.service.LibraryService;

public class Test {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		ApplicationContext ac = null;
		try {
			ac = new ClassPathXmlApplicationContext("beanTest.xml");
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("I am not able to find the xml file");
		}

		LibraryService ls = ac.getBean("libraryService", LibraryService.class);
		int choice = 4;
		while (true) {
			System.out.println("1. add \n2. remove\n3. search\n4. exit\n");
			System.out.println("Enter your choice");
			choice = in.nextInt();
			if (choice == 1)
				ls.addBook();
			else if (choice == 2)
				ls.removeBook();
			else if (choice == 3)
				ls.searchBook();
			else
				break;
		}
	}

}
