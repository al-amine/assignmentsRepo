package assignment_4;



public class Rectangle implements Shape {
	
    private int width;
    private int height;
    
    
    
	public int getWidth() {
		return width;
	}




	public void setWidth(int width) {
		this.width = width;
	}




	public int getHeight() {
		return height;
	}




	public void setHeight(int height) {
		this.height = height;
	}
	
	
    
    public Rectangle() {
		
	}
    
 
    
    public Rectangle(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + height;
		result = prime * result + width;
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
		Rectangle other = (Rectangle) obj;
		if (height != other.height)
			return false;
		if (width != other.width)
			return false;
		return true;
	}




	@Override
	public String toString() {
		return "Rectangle [width=" + width + ", height=" + height + "]";
	}


	public static int CalculateArea(int height, int width) {
		
		return width * height;
	    
    }
	
	public static void display() {
		System.out.println("this is a rectangle !");
	}

}
