package com.lms.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.lms.util.ConnectionPrep;
//import java.sql.Statement;


import com.lms.model.Author;


public class AuthorDao implements Dao<Author> {
	
	
	
	
	private static Connection conn = ConnectionPrep.getConnection();
	private final static String table = "tbl_author";

	

	public AuthorDao(){

	}
	


//	public static Author create(String authorName) throws SQLException{
//		
//		
//		      Statement stmt = conn.createStatement(); 
//	          String queryAccount = "INSERT INTO library1.tbl_author (authorName) VALUES ('"+authorName+"');";
//	          stmt.executeUpdate(queryAccount);
//	     
//	
//	}
	

    public  Author create(String authorName) throws SQLException {
    	
    	Author createdAuthor = null;
    	
    	try {
    		
    		 PreparedStatement prepareStatement = null;
    	        ResultSet resultSet = null;
    	        
    	        String sql = "INSERT INTO " + table + " (authorName) VALUES (?);";
    	        
    	        prepareStatement = conn.prepareStatement(sql);
    	        prepareStatement.setString(1, authorName);
    	        prepareStatement.executeUpdate();
    	        
    	        conn.commit();
    	        
    	        sql = "SELECT * FROM " + table + " ORDER BY authorId DESC LIMIT 1;";
    	        
    	        prepareStatement = conn.prepareStatement(sql);
    	        resultSet = prepareStatement.executeQuery();
    	        resultSet.next();
    	        
    	        createdAuthor = new Author(resultSet.getInt("authorId"), resultSet.getString("authorName"));
    	           
			
		} catch (SQLException e) {
				conn.rollback();
				e.printStackTrace();
	
		}
    	
       
    	return createdAuthor;
        
    }
	
    
    
    
@Override
    public void update(Author author) throws SQLException {
	
	try {
		

	
        PreparedStatement prepareStatement = null;
        
        String sql = "UPDATE " + table + " SET authorName = ? WHERE authorId = ?;";
        
        prepareStatement = conn.prepareStatement(sql);
        prepareStatement.setString(1, author.getName());
        
        prepareStatement.setInt(2, author.getId());
        prepareStatement.executeUpdate();
        
        conn.commit();
        
	} catch (SQLException e) {
		conn.rollback();
		e.printStackTrace();
	}
        
    }
	
	
	
	
	
@Override
    public void delete(Author author) throws SQLException {
	
	try {
		

	
         PreparedStatement prepareStatement = null;
         
         String sql = "DELETE FROM " + table + " WHERE authorId = ?;";
         
         prepareStatement = conn.prepareStatement(sql);
         prepareStatement.setInt(1, author.getId());
         
         prepareStatement.executeUpdate();
         
         conn.commit();
         
	} catch (SQLException e) {
		conn.rollback();
		e.printStackTrace();
	}
  }
	
	
	
	
@Override
public Author get(int id) throws SQLException {
	
	
    PreparedStatement prepareStatement = null;
    ResultSet resultSet = null;
    
    String sql = "SELECT * FROM " + table + " WHERE authorId = ?;";
    
    prepareStatement = conn.prepareStatement(sql);
    prepareStatement.setInt(1, id);
    resultSet = prepareStatement.executeQuery();
    
    Author authorfound = null;
    
      if(resultSet.next()) {
             authorfound = new Author(resultSet.getInt("authorId"), resultSet.getString("authorName"));
         }
      
    return authorfound;
  }
	
	
	
	


	@Override
    public List<Author> getAll() throws SQLException {
		
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        
        List<Author> returnList = new ArrayList<>();
        
          String sql = "SELECT * FROM " + table + ";";
          
        
        prepareStatement = conn.prepareStatement(sql);
        resultSet = prepareStatement.executeQuery();
        
        while (resultSet.next()) {
        	
            returnList.add(new Author(resultSet.getInt("authorId"), resultSet.getString("authorName")));
        }
        
        return returnList;
    }
	
}
