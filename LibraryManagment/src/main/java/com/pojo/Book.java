package com.pojo;

public class Book {
	private String Name;
	private String edition;
	private int isbn;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		this.Name = name;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	@Override
	public String toString() {
		return "Book [Name=" + Name + ", edition=" + edition + ", isbn=" + isbn + "]";
	}

}
