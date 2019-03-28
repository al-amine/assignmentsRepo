package service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import bean.Author;
import bean.Book;
import bean.Publisher;
import dao.BookDao;

public class BookService extends BookDao{
	
	
	
	
    public BookService() {
        super();
    }
    
    
    
    
	
    public int createBook(int id, String title,int authorid, int publisherid,String authorname,String publishername) throws IOException {  
    	
	    AuthorService authorservice = new AuthorService();
	    PublisherService publisherservice = new PublisherService();	
	    
	    Author author = authorservice.find(authorid);
	    Publisher publisher = publisherservice.find(publisherid);
	     
	
	    		
    if (id > 0 ) {
    	
    	if ( author!= null && publisher != null ) {
  		   
  		   
	        Book book = new Book();
	        book.setId(id);
	        book.setTitle(title);
	        book.setAuthorId(authorid);
	        book.setPublisherId(publisherid);

	        create(book);
	        System.out.println("Book Created");
	        return 1;
		
	      }
	   
	   else {
		   
		   System.out.println("these Author and publisher that you entered need to be created first");
		      
		   authorservice.createAuthor(authorid, authorname);
		   publisherservice.createPublisher(publisherid, publishername, "update", "update");
		   
	        Book book = new Book();
	        book.setId(id);
	        book.setTitle(title);
	        book.setAuthorId(authorid);
	        book.setPublisherId(publisherid);

	        create(book);
	        
	        System.out.println("you will have to update the publisher : "+publishername+" ID : "+publisherid+", because we don not have the whole informations.");
    	
    	
     
   
        return 2;
        
		        }
     
    
    

    
       }
    else 
    {
    	
    	System.out.println(" you entered a negative ID.\n");
    	
	        
	        return -1;
    	
  
      }
    
    }
    
  
    
    
    public int updateBook(int id,String newtitle, int newauthorid, int newpublisherid, String newauthorname, String newpublishername) throws FileNotFoundException, IOException {
    	
    	
	    AuthorService authorservice = new AuthorService();
	    PublisherService publisherservice = new PublisherService();	
	    
	    
	    Book book = find(id);
	    Author author = authorservice.find(newauthorid);
	    Publisher publisher = publisherservice.find(newpublisherid);
    	
        if (book == null ) {
        	
            return -1;
        } else {
        	
        	if (author!= null && publisher != null) {
				
            	book.setId(id);
            	book.setTitle(newtitle);
            	book.setAuthorId(newauthorid);
            	book.setPublisherId(newpublisherid);
            	
                update(book);
                
                System.out.println("Book updated");
               return 1; 
			}
        	
        	else {
        		
        		
        		
        		
     		   System.out.println("these Author and publisher that you entered need to be created first");
 		      
     		   authorservice.createAuthor(newauthorid, newauthorname);
     		   publisherservice.createPublisher(newpublisherid, newpublishername, "null", "null");
     		   
    	        book.setId(id);
    	        book.setTitle(newtitle);
    	        book.setAuthorId(newauthorid);
    	        book.setPublisherId(newpublisherid);

    	        update(book);
    	        
    	        System.out.println("Book Updated");
    	        System.out.println("you will have to update the publisher : "+newpublishername+" ID : "+newpublisherid+", because we don not have the whole informations needed.");
    	        
    	        return 2;
     		   
      
			}

        } 
   
   
        

	   
	   
	   
} 
        
 
    
    
    public int deleteBook(int bookid) throws FileNotFoundException, IOException{
    	
    	Book book1 = find(bookid);
    	
        if (book1 == null ) {
            return -1;
        } 
        else {
        	
            delete(book1.getId());
            
            System.out.println("Book Deleted\n");
            
            return 1;
        }
    }
    
    
public int showBooks() throws FileNotFoundException, IOException{
    	
    	List<Book> books = findAll();
    	
        if (books.isEmpty()) {
            return -1;
        } 
        else {
        	
        	System.out.println("Here Are the list of the books Available in our Data Base for now : ");
        	
           
       	books
		.stream()
		.forEach(b -> System.out.println("Book Title : "+b.getTitle()+", and its Database referential ID  is :"+b.getId()));
            
           
            return 1;
        }
    }
    
    
    
    
    
}
    
    
    

    
    
    
    

    
    
    
    
    
    
    
    
    
    


