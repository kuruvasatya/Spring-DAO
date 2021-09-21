package com.service;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BookDAOImp;
import com.pojo.Book;

@Service
public class LibraryService {

	Scanner in = new Scanner(System.in);
	@Autowired
	BookDAOImp service;
	Book book = new Book();

	public void addBook() {
		System.out.println("Enter the book name : ");
		book.setName(in.next());
		System.out.println("Enter book edition : ");
		book.setEdition(in.next());
		System.out.println("Enter the isbn number : ");
		book.setIsbn(in.nextInt());
		Boolean result = service.add(book);
		if (result == true)
			System.out.println("Book Inserted Successfully...");
		else
			System.out.println("Insertion failed....");
	}

	public void removeBook() {
		System.out.println("Enter the isbn number : ");
		Boolean result = service.remove(in.nextInt());
		if (result == true)
			System.out.println("Book Deleted Successfully...");
		else
			System.out.println("Deletion failed....");
	}

	public void searchBook() {
		System.out.println("Enter the isbn number : ");
		Boolean result = service.search(in.nextInt());
		if (result == true)
			System.out.println("Book found...");
		else
			System.out.println("Book is not available....");
	}

}
