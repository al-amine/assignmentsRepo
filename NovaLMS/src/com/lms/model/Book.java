package com.lms.model;

import java.util.Objects;

public class Book {
	
	private int id;
	private String title;
	private Author author;
	private Publisher publisher;

	
	public int getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
           if (author == null) {
        	   
        	   this.author = new Author();
           }	

	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
               if (publisher ==  null) {
            		this.publisher = new Publisher();
			}
		
	}


	public Book() {
	}
	
	

	public Book(int id) {
		this.id = id;
	}
	
	

	public Book(int id, String title) {
		this.id = id;
		this.title = title;
	}
	
	

	public Book(int id, String title, Author author, Publisher publisher) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
	}
	
	



	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return "Book: " + title + " with " + Objects.toString(author, "No Author") + " and " + Objects.toString(publisher, "No Publisher");
	}

	
}
