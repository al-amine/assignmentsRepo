package bean;


import java.io.Serializable;


public class Book implements Serializable {
	
	
	

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

    private int id;
    private String title;
    
    private int authorId;
    private int publisherId;
   
//    private Author author;
//    private Publisher publisher;
    
    
    
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	

//	public Author getAuthor() {
//		
//        if (author ==  null) {
//        author = new Author();      
//        }
// 
//		return author;
//	}
//
//	public void setAuthor(Author author) {
//		this.author = author;
//	}
//
//	public Publisher getPublisher() {
//		
//        if (publisher ==  null) {
//        	publisher = new Publisher();      
//        }
//		return publisher;
//	}
//
//	public void setPublisher(Publisher publisher) {
//		this.publisher = publisher;
//	}

	
	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public int getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
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
	
	
	

	public Book(int id, String title, int authorId, int publisherId) {
		this.id = id;
		this.title = title;
		this.authorId = authorId;
		this.publisherId = publisherId;
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
		return "Book [id=" + id + ", title=" + title + ", authorId=" + authorId + ", publisherId=" + publisherId + "]";
	}


    
	
    
	
    
 
    

}
