package com.lms.dao;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.lms.model.Book;
import com.lms.model.Branch;
import com.lms.util.ConnectionPrep;




public class CopiesDao {
	
	
	
	private static Connection conn = ConnectionPrep.getConnection();
	private final static String table = "tbl_book_copies";
	
	
	

	public CopiesDao(){
	}
	
	
	
public void create(Book book, Branch branch, int numCopies) throws SQLException{
	
	try {
		
		
        PreparedStatement prepareStatement = null;
		
        String sql = "INSERT INTO " + table + " (bookId, branchId,noOfCopies) VALUES (?,?,?);";
        
        prepareStatement = conn.prepareStatement(sql);
        prepareStatement.setInt(1, book.getId());
        prepareStatement.setInt(2, branch.getId());
        prepareStatement.setInt(3, numCopies);

        prepareStatement.executeUpdate();
        
        conn.commit();
        
	} catch (SQLException e) {
		
		conn.rollback();
		e.printStackTrace();
	}
         
		
	}


public void delete(Book book, Branch branch) throws SQLException {
	
	try {
		

    PreparedStatement prepareStatement = null;
    
    String sql = "DELETE FROM " + table + " WHERE bookId = ? AND branchId = ?;";
    
    prepareStatement = conn.prepareStatement(sql);
    prepareStatement.setInt(1, book.getId());
    
    prepareStatement.setInt(2, branch.getId());
    
    prepareStatement.executeUpdate();
    
    conn.commit();
    
	} catch (SQLException e) {
		conn.rollback();
		e.printStackTrace();
	}
	
    
}
	

	
	
	
	public int getCopies(Branch branch, Book book) throws SQLException {
		
		
	    PreparedStatement prepareStatement = null;
	    ResultSet resultSet = null;
	    
	    String sql = "SELECT * FROM " + table + " WHERE bookId = ? AND branchId = ?;";
	    
	    prepareStatement = conn.prepareStatement(sql);
	    prepareStatement.setInt(1, book.getId());
	    prepareStatement.setInt(2, branch.getId());

	    
	    resultSet = prepareStatement.executeQuery();
	    
	   
	    
	      if(resultSet.next()) {
            
	    	  int copies = resultSet.getInt("noOfCopies");
	    	  
	    	  return copies;

	         }
	      
	      else {
	    	  
	    	  return 0;
		}
		
	}
	
	
	
	public void setCopies(Branch branch , Book book, int noOfCopies) throws SQLException, IOException {
		
		try {
			

		
		
	    PreparedStatement prepareStatement = null;
	    int exists = 0;

	    
	    if (noOfCopies != 0) {
	    	
	    	exists = getCopies(branch, book);
	    	
	    	if (exists == 0) {
	    		
	    		create(book, branch, noOfCopies);
	    		
			}
	    	
	    	else {
	    		
			    String sql = "UPDATE " + table + " SET noOfCopies = ? WHERE bookId = ? AND branchId = ?;";
			    
			    prepareStatement = conn.prepareStatement(sql);
			    prepareStatement.setInt(1,noOfCopies);
			    prepareStatement.setInt(2, book.getId());
			    prepareStatement.setInt(3, branch.getId());
			    prepareStatement.executeUpdate();
			    
			    conn.commit();
			    
				
			}
			

		}
	    
	    
	    else {
	    	
	    	delete(book,branch);
			
		}
	    
		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
		}

		
		
	} 
	
	
	public Map<Book, Integer> getAllBranchCopies(Branch branch) throws SQLException, IOException {
		
	  
		
		BookDao bookDao = new BookDao();
		
		
	    PreparedStatement prepareStatement = null;
	    ResultSet resultSet = null;
	    
	    String sql = "SELECT * FROM " + table + " WHERE branchId = ?;";
	    
	    prepareStatement = conn.prepareStatement(sql);
	    prepareStatement.setInt(1, branch.getId());
	
	    
	    resultSet = prepareStatement.executeQuery();
	    
	    Map<Book, Integer>  branchCopies =  new HashMap<>();
	    
	      while(resultSet.next()) {

	          Book b = bookDao.get(resultSet.getInt("bookId"));
	          int no = resultSet.getInt("noOfCopies");
	    	  
	           branchCopies.put(b, no);
	          
	         }
	      
	    return branchCopies;
		
	}
	
	
	public Map<Branch, Integer> getAllBookCopies(Book book) throws SQLException, IOException {
		
		
		LibraryBranchDao libraryBranchDao = new LibraryBranchDao();
		
		
	    PreparedStatement prepareStatement = null;
	    ResultSet resultSet = null;
	    
	    String sql = "SELECT * FROM " + table + " WHERE bookId = ?;";
	    
	    prepareStatement = conn.prepareStatement(sql);
	    prepareStatement.setInt(1, book.getId());
	
	    
	    resultSet = prepareStatement.executeQuery();
	    
	    Map<Branch, Integer>  bookCopies = new HashMap<>();
	    
	      while(resultSet.next()) {

	          Branch b = libraryBranchDao.get(resultSet.getInt("branchId"));
	          int no = resultSet.getInt("noOfCopies");
	    	  
	         bookCopies.put(b, no);
	          
	         }
	      
	    return bookCopies;
		

	}
	
	
	public Map<Branch, Map<Book, Integer>> getAllCopies() throws SQLException, IOException {
		
		
		LibraryBranchDao libraryBranchDao = new LibraryBranchDao();
		BookDao bookDao = new BookDao();

		
		
	    PreparedStatement prepareStatement = null;
	    ResultSet resultSet = null;
	    
	    String sql = "SELECT * FROM " + table + " ;";
	    
	    prepareStatement = conn.prepareStatement(sql);
	
	    
	    resultSet = prepareStatement.executeQuery();
	    
	    Map<Branch, Map<Book, Integer>>  allcopies =  new HashMap<>();

	    
	      while(resultSet.next()) {
	    	  
	          Branch ba = libraryBranchDao.get(resultSet.getInt("branchId"));
        	  
        	  Book b = bookDao.get(resultSet.getInt("bookId"));
	          int no = resultSet.getInt("noOfCopies");

	          
	   
	          
	           if(allcopies.containsKey(ba)) {
	        	   allcopies.get(ba).put(b, no);
	            } else {
	            	
	                Map<Book, Integer> boI = new HashMap<>();
	                boI.put(b, no);
	                allcopies.put(ba, boI);
	            }
	      
	          
	         }
	      
	    return allcopies;
		
		

	}
	
	
	
	
	
}
