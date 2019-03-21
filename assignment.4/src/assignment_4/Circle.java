package assignment_4;

import java.lang.Math;;

public class Circle implements Shape{
	
	
	
	private double radius;
	private double diamter;
	
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	public double getDiamter() {
		return diamter;
	}
	public void setDiamter(double diamter) {
		this.diamter = diamter;
	}
	
	
	public Circle() {
	
	}
	public Circle(double radius, double diamter) {

		this.radius = radius;
		this.diamter = diamter;
	}
	
	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(diamter);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(radius);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Circle other = (Circle) obj;
		if (Double.doubleToLongBits(diamter) != Double.doubleToLongBits(other.diamter))
			return false;
		if (Double.doubleToLongBits(radius) != Double.doubleToLongBits(other.radius))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Circle [radius=" + radius + ", diamter=" + diamter + "]";
	}
	
	
	
	public static double CalculateArea(double radius) {
		
		return ((radius * radius) * Math.PI);
	    
    }
	
	
	public static void display() {
		System.out.println("this is a circle !");
	}
	

}
