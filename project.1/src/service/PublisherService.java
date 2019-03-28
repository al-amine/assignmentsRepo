package service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import bean.Book;
import bean.Publisher;
import dao.PublisherDao;



public class PublisherService  extends PublisherDao{
	
	
	
    public PublisherService() {
        super();
    }
	
	
	   public int createPublisher(int id, String name,String address, String phone) throws IOException {
		   

	        if (id < 0 ) {
	        	
	        	System.out.println("you entered a negative ID.\n");
	            return -1;
	            
	        } 
	        else {
	            Publisher publisher = new Publisher();
	            publisher.setId(id);
	            publisher.setName(name);
	            publisher.setAddress(address);
	            publisher.setPhone(phone);

	            create(publisher);
	            System.out.println("Publisher Created\n");
	            return 1;
	        }
	    }
	   
	   

	    public int updatePublisher(int id,String newName, String newaddress, String newphone) throws FileNotFoundException, IOException {
	    	
	    	Publisher publisher = find(id);
	    	
	        if (publisher == null ) {
	            return -1;
	        } else {
	        	
	        	publisher.setId(id);
	        	publisher.setName(newName);
	        	publisher.setAddress(newaddress);
	        	publisher.setPhone(newphone);
	        	

	            update(publisher);
	            
	            System.out.println("Publisher Updated");
	            return 1;
	        } 
	   
	   
      
	        
	        
	        
	               
	   
}
	    
        public int deletePublisher(int publisherid) throws FileNotFoundException, IOException{
        	
    	    BookService bookservice1 = new BookService();	
        	
        	Publisher publisher1 = find(publisherid);
        	
            if (publisher1 == null ) {
                return -1;
            } 
            else {
            	
            	List<Book> books = bookservice1.findAll();
            	
               for (Book b : books) {
            	   
            	    if (b.getPublisherId() == publisherid ) {
            	    	
            	    	bookservice1.delete(b.getId());
						
               }
	
                 }
            	
                delete(publisher1.getId());
                
                System.out.println("Publisher deleted");
                return 1;
            }
        }

        
        
        public int showPublisherAndBooks() throws FileNotFoundException, IOException{
	    	   
	    	   
	    	   BookService bookService = new BookService();
	    	   
	    	   
	       	List<Publisher> publishers = findAll(); 
	       	List<Book> books = bookService.findAll();
	       	
	           if (books.isEmpty() && publishers.isEmpty() ) {
	               return -1;
	           } 
	           else {
	           	
	           	System.out.println("Here is the list of the Publishers and their books Available in our Data Base for now : ");
	           	
	              for (Publisher p : publishers) {
	           	   
	           	   System.out.println("Publisher's full Name : "+p.getName()+", and his/her Database referential ID  is :"+p.getId());
	           	   
		          	books
			   		.stream()
			   		.filter(b -> b.getPublisherId() == p.getId())
			   		.forEach(b -> System.out.println("Book Title : "+b.getTitle()+", and its Database referential ID  is :"+b.getId()));
	           	   
	   		    }
	              

	               
	              
	               return 1;
	           }
	       }    
   
        
        public int showPublisher () throws FileNotFoundException, IOException{
	    	  
    	   	List<Publisher> publishers = findAll(); 

    	   	
    	       if ( publishers.isEmpty() ) {
    	           return -1;
    	       } 
    	       else {
    	       	
    	       	System.out.println("Here is the list of the Publishers : ");
    	       	
    	          
    	       	publishers
    		   		.stream()
    		   		.forEach(a -> System.out.println("Publisher's full Name : "+a.getName()+", and his/her Database referential ID  is :"+a.getId()));
    	          

    	           
    	          
    	           return 1;
    	       }
    	   }
        
}
