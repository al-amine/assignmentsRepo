package com.lms.model;



public final class Author {
	
	private int id;
	private String name;


	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public int getId() {
		return id;
	}


	public Author(){

	}

	public Author(int id) {
		this.id = id;
	}

	public Author(int id, String name) {
		this.id = id;
		this.name = name;
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
		Author other = (Author) obj;
		if (id != other.id)
			return false;
		return true;
	}
	

	@Override
	public String toString() {
		return "Author: " + name + "(" + id + ")";
	}


}
