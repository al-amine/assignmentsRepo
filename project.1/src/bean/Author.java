package bean;

import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;


public class Author implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
    private String name;
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
	
//	public List<Book> getBooks() {
//		
//		if( books == null ){
//	        books = new ArrayList<>();
//		}
//		return books;
//	}
//	
//	
//	public void setBooks(List<Book> books) {
//		this.books = books;
//	}

	public Author() {

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
		return "Author [id=" + id + ", name=" + name + "]";
	}




    
    
	
    
    
	
	

}
