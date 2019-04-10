package com.lms.model;



public class Branch {
	
	private  int id;
	private String name;
	private String address;
	

	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

    
	

	public Branch() {
		
	}
	
	

	public Branch(int id) {
		this.id = id;
	}
	
	

	public Branch(int id, String name, String address) {
		this.id = id;
		this.name = name;
		this.address = address;
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
		Branch other = (Branch) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Branch: " + name + "(" + id + ") at " + address;
	}
	
	
	
}
