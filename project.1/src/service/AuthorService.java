package service;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import bean.Author;
import bean.Book;
import dao.AuthorDao;

public class AuthorService extends AuthorDao {
	
    public AuthorService() {
        super();
    }
	
	
	
	   public int createAuthor(int id, String name) throws IOException {
		   

		   
	        if (id < 0 ) {
	        	
	        	System.out.println(" you entered a negative ID.\n");
	        	 
	            return -1;
	            
	        } 
	        else {
	            Author author = new Author();
	            author.setId(id);
	            author.setName(name);

	            create(author);
	            System.out.println("Author Created");
	            return 1;
	        }
	    }
	   
	   

	    public int updateAuthor(int id,String newName) throws FileNotFoundException, IOException {
	    	
	    	Author author = find(id);
	    	
	        if (author == null ) {
	            return -1;
	        } else {
	        	
	            author.setId(id);
	            author.setName(newName);
	            update(author);
	            System.out.println("Author Updated");
	            return 1;
	        } 
	   
	   
	   
}
	    
	    
	    
	       public int deleteAuthor(int authorid) throws FileNotFoundException, IOException{
	        	
	    	    BookService bookservice = new BookService();	
	        	
	        	Author author = find(authorid);
	        	
	            if (author == null ) {
	                return -1;
	            } 
	            else {
	            	
	            	List<Book> books = bookservice.findAll();
	            	
	               for (Book b : books) {
	            	   
	            	    if (b.getAuthorId() == authorid ) {
	            	    	
	            	    	bookservice.delete(b.getId());
							
	               }
		
	                 }
	            	
	                delete(author.getId());
	                
	                System.out.println("Author deleted");
	                return 1;
	            }
	        }
	       
	       
	       public int showAuthorsAndBooks() throws FileNotFoundException, IOException{
	    	   
	    	   
	    	   BookService bookService = new BookService();
	    	   
	    	   
	       	List<Author> authors = findAll(); 
	       	List<Book> books = bookService.findAll();
	       	
	           if (books.isEmpty() && authors.isEmpty() ) {
	               return -1;
	           } 
	           else {
	           	
	           	System.out.println("Here is the list of the Authors and their books Available in our Data Base for now : ");
	           	
	              for (Author a : authors) {
	           	   
	           	   System.out.println("Author's full Name : "+a.getName()+", and his/her Database referential ID  is :"+a.getId());
	           	   
		          	books
			   		.stream()
			   		.filter(b -> b.getAuthorId() == a.getId())
			   		.forEach(b -> System.out.println("Book Title : "+b.getTitle()+", and its Database referential ID  is :"+b.getId()));
	           	   
	   		    }
	              

	               
	              
	               return 1;
	           }
	       }    

      
	       
	       public int showAuthors () throws FileNotFoundException, IOException{
	    	  
	    	   	List<Author> authors = findAll(); 

	    	   	
	    	       if ( authors.isEmpty() ) {
	    	           return -1;
	    	       } 
	    	       else {
	    	       	
	    	       	System.out.println("Here is the list of the Authors : ");
	    	       	
	    	          
	    	          authors
	    		   		.stream()
	    		   		.forEach(a -> System.out.println("Author's full Name : "+a.getName()+", and his/her Database referential ID  is :"+a.getId()));
	    	          

	    	           
	    	          
	    	           return 1;
	    	       }
	    	   }    
	       
	       
	       
	       
	       
}




    


