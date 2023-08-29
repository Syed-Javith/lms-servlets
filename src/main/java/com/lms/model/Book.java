package com.lms.model;

public class Book {
	private String book_name ;
	private String book_author;
	private String book_isbn;
	private String book_description;
	
	public Book(String book_name , String book_author , String book_isbn , String book_description) {
		this.book_author = book_author;
		this.book_isbn = book_isbn;
		this.book_name = book_name;
		this.book_description = book_description;
		
	}
	
	public String getBook_name() {
        return book_name;
    }

    public String getBook_author() {
        return book_author;
    }

    public String getBook_isbn() {
        return book_isbn;
    }
    
    public String getBook_description() {
    	return book_description;
    }
}
