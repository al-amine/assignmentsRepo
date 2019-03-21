package assignment_4;

public class Triangle implements Shape{
	
	
	private double side1 ;
    private double side2 ;
    private double base ;
    private double height ;
    
    
    
	public double getSide1() {
		return side1;
	}
	public void setSide1(double side1) {
		this.side1 = side1;
	}
	public double getSide2() {
		return side2;
	}
	public void setSide2(double side2) {
		this.side2 = side2;
	}
	public double getBase() {
		return base;
	}
	public void setBase(double base) {
		this.base = base;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	
	
	public Triangle() {

	}
	
	
	public Triangle(double side1, double side2, double base, double height) {
		super();
		this.side1 = side1;
		this.side2 = side2;
		this.base = base;
		this.height = height;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(base);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(height);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(side1);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(side2);
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
		Triangle other = (Triangle) obj;
		if (Double.doubleToLongBits(base) != Double.doubleToLongBits(other.base))
			return false;
		if (Double.doubleToLongBits(height) != Double.doubleToLongBits(other.height))
			return false;
		if (Double.doubleToLongBits(side1) != Double.doubleToLongBits(other.side1))
			return false;
		if (Double.doubleToLongBits(side2) != Double.doubleToLongBits(other.side2))
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {
		return "Triangle [side1=" + side1 + ", side2=" + side2 + ", base=" + base + ", height=" + height + "]";
	}
    
    
	

	public static double CalculateArea(double height, double base) {
		
		return (base * height)/2;
	    
    }
	
	public static void display() {
		System.out.println("this is a triangle !");
	}
	
	

}
