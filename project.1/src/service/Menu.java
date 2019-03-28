package service;

import java.io.IOException;
import java.util.Scanner;

import bean.Publisher;


public class Menu {

	
	
	public static void menuAuthor() throws IOException {
		
        AuthorService authorservice = new AuthorService();	
		
		Scanner sc1 =new Scanner(System.in);
		int c1;
		
	    while(true)
	  {
        System.out.println("01 -> Create an Authors");
        System.out.println("02 -> Update an Authors");
        System.out.println("03 -> Remove an Authors");
        System.out.println("04 -> Show all Authors");
        System.out.println("05 -> Back to the principal Menu");
        System.out.print("Enter Your Choice (1,5) : ");
        c1=sc1.nextInt();
        switch(c1) 
        {
        
        case 1: 
        	
      authorservice.showAuthors();
      System.out.print("Make sure you don't Dublicate The ID, the app will not allow that\n");
      
      Scanner reader1 = new Scanner(System.in);
      Scanner reader2 = new Scanner(System.in);
      
          System.out.print("Enter the Author's full name : ");
          String name = reader1.nextLine();
          System.out.print("Enter the Author's Idintification Number (ID) : ");
          int id = reader2.nextInt();
          
          authorservice.createAuthor(id, name);
          System.out.println("\n");

             break;
        case 2:  
        	

        	
        	 authorservice.showAuthors();
             System.out.print("Choose the ID of the Author that you you wish to Update\n");
             
             Scanner reader3 = new Scanner(System.in);
             Scanner reader4 = new Scanner(System.in);

             System.out.print("Enter the  Idintification Number (ID) of the Author you wish to Update : ");
             int newid = reader4.nextInt();
             System.out.print("Enter the Author's New full name : ");
             String newname = reader3.nextLine();
             
             authorservice.updateAuthor(newid, newname);
             System.out.println("\n");

             break;
        case 3:  

       	 authorservice.showAuthors();
       	 
         System.out.print("Choose the ID of the Author that you wish to Delete\n");
         System.out.println("Notice : the Deletion of a given Author leads to the Deletion of the books written by that Author from our DataBase.\n ");
         
         Scanner reader5 = new Scanner(System.in);

         System.out.print("Enter the  Idintification Number (ID) of the Author you wish to Delete : ");
         int deleteid = reader5.nextInt();
         
         authorservice.deleteAuthor(deleteid);
         System.out.println("\n");
        	
        	
             break;
             
        case 4:  
        	
        	System.out.print("you will find the List of Authors registered in our DataBase shown Below\n\n");
        	authorservice.showAuthors();
        	 System.out.println("\n");

             break; 
 
        case 5:
        	mainMenu();
        	System.out.println("\n");
        	System.exit(0);
        break;
        default: System.out.println("Wrong Entry");
                 
             
          
        }
	  }
		
	}
	
	
	public static void menuPublisher() throws IOException {
		
	    PublisherService publisherservice = new PublisherService();		
		
		Scanner sc2 =new Scanner(System.in);
		int c2;
		
	    while(true)
	  {
	    	   
	           System.out.println("01 - Create a Publisher");
	           System.out.println("02 - Update a Publisher");
	           System.out.println("03 - Remove a Publisher");
	           System.out.println("04 - Show all Publishers");
	           System.out.println("05 - Back to the principal Menu");
	           System.out.print("Enter Your Choice (1,5) : ");
	           c2=sc2.nextInt();
	           switch(c2) 
	           {
	           
            case 1:  
            	
            	publisherservice.showPublisher();
                System.out.print("Make sure you don't Dublicate The ID, the app will not allow that\n");
                
                Scanner reader1 = new Scanner(System.in);
                Scanner reader2 = new Scanner(System.in);
                Scanner reader3 = new Scanner(System.in);
                Scanner reader4 = new Scanner(System.in);
                
                    System.out.print("Enter the Publisher's full Name : ");
                    String name = reader1.nextLine();
                    System.out.print("Enter the Publisher's full Adress : ");
                    String address = reader2.nextLine();
                    System.out.print("Enter the Publisher's Phone number : ");
                    String phone = reader3.nextLine();
                    System.out.print("Enter the Publisher's Idintification Number (ID) : ");
                    int id = reader4.nextInt();
                    
                    publisherservice.createPublisher(id, name, address, phone);
                    System.out.println("\n");
                 break;
                 
            case 2: 
            	
            	publisherservice.showPublisher();
                System.out.print("Choose the ID of the Author that you you wish to Update\n");
                
                Scanner reader5 = new Scanner(System.in);
                Scanner reader6 = new Scanner(System.in);
                Scanner reader7 = new Scanner(System.in);
                Scanner reader8 = new Scanner(System.in);
                
                System.out.print("Enter the Publisher's Idintification Number (ID) : ");
                int newid = reader8.nextInt();
                
                    System.out.print("Enter the Publisher's new full Name : ");
                    String newname = reader5.nextLine();
                    System.out.print("Enter the Publisher's new full Adress : ");
                    String newaddress = reader6.nextLine();
                    System.out.print("Enter the Publisher's  new Phone number : ");
                    String newphone = reader7.nextLine();

                    
                    publisherservice.updatePublisher(newid, newname, newaddress, newphone);
                    System.out.println("\n");
                 break;
                 
            case 3:  
            	
              	 publisherservice.showPublisher();
               	 
                 System.out.print("Choose the ID of the Author that you wish to Delete\n");
                 System.out.println("Notice : the Deletion of a given Publisher leads to the Deletion of the books published by that publisher from our DataBase.\n ");
                 
                 Scanner reader9 = new Scanner(System.in);

                 System.out.print("Enter the  Idintification Number (ID) of the Publisher you wish to Delete : ");
                 int deleteid = reader9.nextInt();
                 
                 publisherservice.deletePublisher(deleteid);
                 System.out.println("\n");
            	

                 break;
                 
            case 4:  
            	
            	publisherservice.showPublisherAndBooks();
            	 System.out.println("\n");
            	

                 break; 
 
        case 5:
        	mainMenu();
        	System.out.println("\n");
        	System.exit(0);
        break;
        default: System.out.println("Wrong Entry");
                 
             
          
        }
	  }
		
	}
	
	
	
	
public static void menuBook() throws IOException {
	
    BookService bookservice  = new BookService();
    PublisherService publisherservice = new PublisherService();
    AuthorService authorservice = new AuthorService();
		
		Scanner sc3 =new Scanner(System.in);
		int c3;
		
	    while(true)
	  {
	    	   
	    	   System.out.println("01 - Create a Book");
	           System.out.println("02 - Update a Book");
	           System.out.println("03 - Remove a Book");
	           System.out.println("04 - Show all Books");
	           System.out.println("05 - Back to the principal Menu");
	           System.out.print("Enter Your Choice (1,5) : ");
	           c3=sc3.nextInt();
	           switch(c3) 
	           {
	           
             case 1: 
            	 
            	bookservice.showBooks();
             	System.out.println("\n");
             	System.out.println("-------------------------------------\n");
             	System.out.println("\n");
            	authorservice.showAuthors();
            	System.out.println("\n");
            	System.out.println("-------------------------------------\n");
            	System.out.println("\n");
             	publisherservice.showPublisher();
             	System.out.println("\n");
             	System.out.println("\n");
             	
                System.out.print("Make sure the Author and the Publisher of the book you wish to update are on those lists above.\n");
                System.out.print("if not, no worries you will have to provide me with the infos of the new Author and publisher of that book.\n");
                System.out.print("And again Make sure you don't Dublicate The IDs of the book, Author and Publisher, the app will not allow that\n");
                
                
                
                Scanner reader1 = new Scanner(System.in);
                Scanner reader2 = new Scanner(System.in);
                Scanner reader3 = new Scanner(System.in);
                Scanner reader4 = new Scanner(System.in);
                Scanner reader5 = new Scanner(System.in);
                Scanner reader6 = new Scanner(System.in);
                Scanner reader7 = new Scanner(System.in);
                Scanner reader8 = new Scanner(System.in);
                
                    System.out.print("Enter the Book's full Title : ");
                    String title = reader1.nextLine();
                    System.out.print("Enter the Publisher's full Name : ");
                    String publishername = reader2.nextLine();
                    System.out.print("Enter the Author's full name : ");
                    String authorname = reader3.nextLine();
                    System.out.print("Enter the Book's Idintification Number (ID) : ");
                    int bookid = reader4.nextInt();
                    System.out.print("Enter the Author's Idintification Number (ID) : ");
                    int authorid = reader5.nextInt();
                    System.out.print("Enter the Publisher's Idintification Number (ID) : ");
                    int publisherid = reader6.nextInt();
                    
                    bookservice.createBook(bookid, title, authorid, publisherid, authorname, publishername);
                    
                 
                    Publisher p = publisherservice.find(publisherid);
                    
                    
                    if (p.getAddress().equals("update") && p.getPhone().equals("update")) {
                    	
                    System.out.print(" Now, would you please provide me with the full address and phone number of the new publisher : "+publishername);
                 	System.out.println("\n");
                 	
                    System.out.print("Enter the new Publisher's full Address : ");
                    String publisheraddress = reader2.nextLine();
                    System.out.print("Enter the new Publisher's Phone number : ");
                    String publisherphone = reader3.nextLine();
                    
                    
                    
                    publisherservice.updatePublisher(publisherid, publishername, publisheraddress, publisherphone);
                    
                    }
                    
                    System.out.println("\n");

                  break;
                  
             case 2:  
            	 

            	authorservice.showAuthors();
            	System.out.println("\n");
            	System.out.println("--------------------------------------\n");
            	System.out.println("\n");
             	publisherservice.showPublisher();
             	System.out.println("\n");
             	
                System.out.print("Make sure the Author and the Publisher of the book you wish to update are on those lists above.\n");
                System.out.print("if not, no worries you will have to provide me with the infos of the new Author and publisher of that book.\n");
                System.out.print("And again Make sure you don't Dublicate The IDs of the book, Author and Publisher, the app will not allow that\n");
               	bookservice.showBooks();
             	System.out.println("\n");
             	System.out.println("\n");
                System.out.print("Choose the ID of the Book that you you wish to Update\n");
                
                
                Scanner reader9 = new Scanner(System.in);
                Scanner reader10 = new Scanner(System.in);
                Scanner reader11 = new Scanner(System.in);
                Scanner reader12 = new Scanner(System.in);
                Scanner reader13 = new Scanner(System.in);
                Scanner reader14 = new Scanner(System.in);
                Scanner reader15 = new Scanner(System.in);
                Scanner reader16 = new Scanner(System.in);
                
                System.out.print("Enter the Book's Idintification Number (ID) : ");
                int booknewid = reader9.nextInt();
                
                    System.out.print("Enter the Book's New full Title : ");
                    String newtitle = reader10.nextLine();
                    System.out.print("Enter the Publisher's new  full Name : ");
                    String newpublishername = reader12.nextLine();
                    System.out.print("Enter the Author's  new full name : ");
                    String newauthorname = reader13.nextLine();

                    System.out.print("Enter the Author's new Idintification Number (ID) : ");
                    int newauthorid = reader15.nextInt();
                    System.out.print("Enter the Publisher's new Idintification Number (ID) : ");
                    int newpublisherid = reader16.nextInt();
                    
                    bookservice.updateBook(booknewid, newtitle, newauthorid, newpublisherid, newauthorname, newpublishername);
                    
                    Publisher p1 = publisherservice.find(newpublisherid);
                    
                    if (p1.getAddress().equals("update") && p1.getPhone().equals("update")) {
                    	
                        System.out.print(" Now, would you please provide me with the full address and phone number of the new publisher : "+newpublishername);
                     	System.out.println("\n");
                     	
                        System.out.print("Enter the new Publisher's full Address : ");
                        String newpublisheraddress = reader9.nextLine();
                        System.out.print("Enter the new Publisher's Phone number : ");
                        String newpublisherphone = reader10.nextLine();
                        
                        
                        
                        publisherservice.updatePublisher(newpublisherid, newpublishername, newpublisheraddress, newpublisherphone);
					}
                    

                    System.out.println("\n");
            	 
            	 

                  break;
                  
             case 3:  
            	 
            		bookservice.showBooks();
                 	System.out.println("\n");
                 	System.out.println("\n");
                    System.out.print("Choose the ID of the Book that you you wish to Delete\n");
                 	
                    
                    Scanner reader20 = new Scanner(System.in);
                    System.out.print("Enter the Author's new Idintification Number (ID) : ");
                    int idbook = reader20.nextInt();
                    
                    
                    bookservice.deleteBook(idbook);

                  break;
                  
             case 4:  
            	 
             bookservice.showBooks();
           	 System.out.println("\n");

                  break; 
 
        case 5:
        	mainMenu();
        	System.exit(0);
        break;
        default: System.out.println("Wrong Entry");
                 
             
          
        }
	  }
		
	}


public static void mainMenu() throws IOException {
	
    Scanner sc =new Scanner(System.in);
    int ch;
    while(true)
    {
        System.out.println("01 - The Authors Menu");
        System.out.println("02 - The Publishers Menu");
        System.out.println("03 - The Books Menu");
        System.out.println("04 - Quit");
        System.out.print("Enter Your Choice (1,4) : ");
        ch=sc.nextInt();
        switch(ch)
        {
            case 1:menuAuthor();
         	  break;
         	  
            case 2:menuPublisher();
         	  break;

            case 3:menuBook();
         	  break;

            case 4:
            	System.out.println("Thank you, see you later.");
            	System.exit(0);
            default: System.out.println("Wrong Entry");
         }
    }
	
}
	
	
	public static void main(String[] args) throws IOException {
		
	    mainMenu();
	    
//main menu
	    
	   }
	

	}


