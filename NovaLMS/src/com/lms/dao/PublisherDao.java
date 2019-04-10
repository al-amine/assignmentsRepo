package com.lms.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lms.model.Publisher;
import com.lms.util.ConnectionPrep;

public class PublisherDao implements Dao<Publisher> {
	
	
	
	private static Connection conn = ConnectionPrep.getConnection();
	private final static String table = "tbl_publisher";

	

	public PublisherDao(){

	}
	
	
	public Publisher create(String publisherName, String publisherAddress, String publisherPhone) throws SQLException {
		
		Publisher createdPublisher = null;
		
		try {
			

        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        
        String sql = "INSERT INTO " + table + " (publisherName, publisherAddress, publisherPhone) VALUES (?, ?, ?);";
        
        prepareStatement = conn.prepareStatement(sql);
        prepareStatement.setString(1, publisherName);
        
        prepareStatement.setString(2, publisherAddress);
        prepareStatement.setString(3, publisherPhone);
        
        prepareStatement.executeUpdate();
        
        conn.commit();
        
        
        sql = "SELECT * FROM " + table + " ORDER BY publisherId DESC LIMIT 1;";
        
        prepareStatement = conn.prepareStatement(sql);
        resultSet = prepareStatement.executeQuery();
        resultSet.next();
        
        createdPublisher = new Publisher(resultSet.getInt("publisherId"), resultSet.getString("publisherName"),
        		resultSet.getString("publisherAddress"), resultSet.getString("publisherPhone"));
        
		} catch (SQLException e) {
			
			conn.rollback();
			e.printStackTrace();
		}
		
        
        
		return createdPublisher;
	}

	
	
	
	
	@Override
    public void update(Publisher publisher) throws SQLException {
		
		try {
			

	
        PreparedStatement prepareStatement = null;
        
        String sql = "UPDATE " + table + " SET publisherName = ?, publisherAddress = ?, publisherPhone = ? WHERE publisherId = ?;";
        
        prepareStatement = conn.prepareStatement(sql);
        
        prepareStatement.setString(1, publisher.getName());
        prepareStatement.setString(2, publisher.getAddress());
        
        prepareStatement.setString(3, publisher.getPhone());
        prepareStatement.setInt(4, publisher.getId());
        
        prepareStatement.executeUpdate();
        
        conn.commit();
        
		} catch (SQLException e) {
			
			conn.rollback();
			e.printStackTrace();
		}
        
    }
	
	

	
	@Override
    public void delete(Publisher publisher) throws SQLException {
		
		try {
		
	
         PreparedStatement prepareStatement = null;
         
         String sql = "DELETE FROM " + table + " WHERE publisherId = ?;";
         
         prepareStatement = conn.prepareStatement(sql);
         prepareStatement.setInt(1, publisher.getId());
         
         prepareStatement.executeUpdate();
         
         conn.commit();
         
		} catch (SQLException e) {
			
			conn.rollback();
			e.printStackTrace();
		}
  }

	@Override
	public Publisher get(int id) throws SQLException {
		
		
	    PreparedStatement prepareStatement = null;
	    ResultSet resultSet = null;
	    
	    String sql = "SELECT * FROM " + table + " WHERE publisherId = ?;";
	    
	    prepareStatement = conn.prepareStatement(sql);
	    prepareStatement.setInt(1, id);
	    resultSet = prepareStatement.executeQuery();
	    
	   Publisher publisherfound = null;
	    
	      if(resultSet.next()) {
	             publisherfound = new Publisher(resultSet.getInt("publisherId"), resultSet.getString("publisherName"),
	            		 resultSet.getString("publisherAddress"),resultSet.getString("publisherPhone"));
	         }
	      
	    return publisherfound;
	  }
	
	
	
	

	@Override
    public List<Publisher> getAll() throws SQLException {
		
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        
        List<Publisher> returnList = new ArrayList<>();
        
          String sql = "SELECT * FROM " + table + ";";
         
        
        prepareStatement = conn.prepareStatement(sql);
        resultSet = prepareStatement.executeQuery();
        
        while (resultSet.next()) {
        	
            returnList.add(new Publisher(resultSet.getInt("publisherId"), resultSet.getString("publisherName"),
            		resultSet.getString("publisherAddress"),resultSet.getString("publisherPhone")));
        }
        
        return returnList;
    }
}
