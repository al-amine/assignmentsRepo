package com.lms.model;


public class Borrower {
	
	private  int cardNo;
	private String name;
	private String address;
	private String phone;
	


	
	public int getCardNo() {
		return cardNo;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	

	public Borrower() {
		
	}
	

	public Borrower(int cardNo) {
		this.cardNo = cardNo;
	}
	

	public Borrower(int cardNo, String name, String address, String phone) {
		this.cardNo = cardNo;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}
	
	

	
	


	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cardNo;
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
		Borrower other = (Borrower) obj;
		if (cardNo != other.cardNo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Borrower " + name + "(" + cardNo + ") at " + address + " with phone: " + phone;
	}
	
	
	
	
}
