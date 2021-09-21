package com.dao;

import com.pojo.Book;

public interface BookDAO {
	
	public boolean add(Book book);

	public boolean remove(int isbn);

	public boolean search(int isbn);
}
