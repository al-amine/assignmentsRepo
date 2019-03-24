package com.st.lms.model;

public class Employee {

	private int id; 
	private String name;
	private int Salary;
	private Integer managerId;
	private Department department;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSalary() {
		return Salary;
	}
	public void setSalary(int salary) {
		Salary = salary;
	}
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	
	
	public Department getDepartment() {
		if (department == null) {
			department = new Department();
		}
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	
	public Employee() {
	}
	public Employee(int id) {
		this.id = id;
	}
	public Employee(int id, String name, int salary, Integer managerId) {
		this.id = id;
		this.name = name;
		Salary = salary;
		this.managerId = managerId;
	}
	public Employee(int id, String name, int salary, Integer managerId, Department department) {
		this.id = id;
		this.name = name;
		Salary = salary;
		this.managerId = managerId;
		this.department = department;
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
		Employee other = (Employee) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
	
	
	
}
