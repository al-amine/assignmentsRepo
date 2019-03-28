package bean;


//import java.util.ArrayList;
//import java.util.List;
import java.io.Serializable;


public class Publisher implements Serializable {
	
	

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
    private String name;
    private String address;
    private String phone;
//    private List<Book> books;
    
    
    
    
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
	
	

//	public List<Book> getBooks() {
//		if( books == null ){
//	        books = new ArrayList<>();
//		}
//		return books;
//	}
//
//	public void setBooks(List<Book> books) {
//		this.books = books;
//	}
	
	
	

	public Publisher() {
	}

	
	public Publisher(int id) {
		this.id = id;
	}

	
	public Publisher(int id, String name, String address, String phone) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
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
		Publisher other = (Publisher) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Publisher [id=" + id + ", name=" + name + ", address=" + address + ", phone=" + phone + "]";
	}
    
    
	
	
	
    
    
	

}
