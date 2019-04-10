package com.lms.dao;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lms.model.Author;
import com.lms.model.Book;
import com.lms.model.Publisher;
import com.lms.util.ConnectionPrep;

public class BookDao implements Dao<Book> {
	
	
	
	private static Connection conn = ConnectionPrep.getConnection();
	private final static String table = "tbl_book";

	

	public BookDao(){
		
	}
	
	
	
	
	public Book create(String title, Author author, Publisher publisher) throws SQLException, IOException {
		
		Book createrbook = null;
		
		try {
			

		
		AuthorDao authorDao = new AuthorDao();
		PublisherDao publisherDao = new PublisherDao();
		
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
		
        String sql = "INSERT INTO " + table + " (title, authId, pubId) VALUES (?,?,?);";
        
        prepareStatement = conn.prepareStatement(sql);
        prepareStatement.setString(1, title);
        prepareStatement.setInt(2, author.getId());
        prepareStatement.setInt(3, publisher.getId());
        prepareStatement.executeUpdate();
        
        conn.commit();
        
        sql = "SELECT * FROM " + table + " ORDER BY bookId DESC LIMIT 1;";
        
        prepareStatement = conn.prepareStatement(sql);
        resultSet = prepareStatement.executeQuery();
        resultSet.next();
        
        Author a = authorDao.get(resultSet.getInt("authId"));
        Publisher p = publisherDao.get(resultSet.getInt("pubId"));
        
        
        createrbook = new Book(resultSet.getInt("bookId"), resultSet.getString("title"),a,p);
        
		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();

			
		}
        
        return createrbook;
        

	}

	
	

	@Override
    public void update(Book book) throws SQLException {
		
		try {
			

	
        PreparedStatement prepareStatement = null;
        
        String sql = "UPDATE " + table + " SET title = ?, authId = ?, pubId = ? WHERE bookId = ?;";
        
        prepareStatement = conn.prepareStatement(sql);
        
        prepareStatement.setString(1, book.getTitle());
        
        prepareStatement.setInt(2, book.getAuthor().getId());
        
        prepareStatement.setInt(3, book.getPublisher().getId());

        prepareStatement.setInt(4, book.getId());
        
        prepareStatement.executeUpdate();
        
        conn.commit();
        
        
		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();

		}
        
    }

	
	
	
	@Override
    public void delete(Book book) throws SQLException {
	
		try {
			

		
         PreparedStatement prepareStatement = null;
         
         String sql = "DELETE FROM " + table + " WHERE bookId = ?;";
         
         prepareStatement = conn.prepareStatement(sql);
         prepareStatement.setInt(1, book.getId());
         
         prepareStatement.executeUpdate();
         
         conn.commit();
         
		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
			// TODO: handle exception
		}
  }

	
	@Override
	public Book get(int id) throws SQLException, IOException {
		
		AuthorDao authorDao = new AuthorDao();
		PublisherDao publisherDao = new PublisherDao();
		
		
	    PreparedStatement prepareStatement = null;
	    ResultSet resultSet = null;
	    
	    String sql = "SELECT * FROM " + table + " WHERE bookId = ?;";
	    
	    prepareStatement = conn.prepareStatement(sql);
	    prepareStatement.setInt(1, id);
	    resultSet = prepareStatement.executeQuery();
	    
	   Book bookfound = null;
	    
	      if(resultSet.next()) {
	    	  
	          Author a = authorDao.get(resultSet.getInt("authId"));
	          Publisher p = publisherDao.get(resultSet.getInt("pubId"));
	    	  
	             bookfound = new Book(resultSet.getInt("BookId"), resultSet.getString("Title"),a,p);
	         }
	      
	    return bookfound;
	  }
	

	@Override
    public List<Book> getAll() throws SQLException, IOException {
		
		AuthorDao authorDao = new AuthorDao();
		PublisherDao publisherDao = new PublisherDao();
		
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        
        List<Book> returnList = new ArrayList<>();
        
          String sql = "SELECT * FROM " + table + ";";
         
        
        prepareStatement = conn.prepareStatement(sql);
        resultSet = prepareStatement.executeQuery();
        
        while (resultSet.next()) {
        	
	          Author a = authorDao.get(resultSet.getInt("authId"));
	          Publisher p = publisherDao.get(resultSet.getInt("pubId"));
	    	  
        	
            returnList.add(new Book(resultSet.getInt("bookId"), resultSet.getString("Title"),a,p));
        }
        
        return returnList;
    }
	
	
}
