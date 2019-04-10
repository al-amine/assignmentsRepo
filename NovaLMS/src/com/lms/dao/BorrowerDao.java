package com.lms.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lms.model.Borrower;
import com.lms.util.ConnectionPrep;

public class BorrowerDao implements Dao<Borrower> {
	
	
	
	
	private static Connection conn = ConnectionPrep.getConnection();
	private final static String table = "tbl_borrower";
	
	

	public BorrowerDao(){

	}
	
	
	
	
	public Borrower create(String borrowerName, String borrowerAddress, String borrowerPhone) throws SQLException {
		
		Borrower createdBorrower = null;
		
		try {
			

        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        
        String sql = "INSERT INTO " + table + " (name, address, phone) VALUES (?, ?, ?);";
        
        prepareStatement = conn.prepareStatement(sql);
        prepareStatement.setString(1, borrowerName);
        
        prepareStatement.setString(2, borrowerAddress);
        prepareStatement.setString(3, borrowerPhone);
        
        prepareStatement.executeUpdate();
        
        conn.commit();
        
        
        sql = "SELECT * FROM " + table + " ORDER BY cardNo DESC LIMIT 1;";
        
        prepareStatement = conn.prepareStatement(sql);
        resultSet = prepareStatement.executeQuery();
        resultSet.next();
        
        createdBorrower = new Borrower(resultSet.getInt("cardNo"), resultSet.getString("name")
        		,resultSet.getString("address"), resultSet.getString("phone"));
        
        
		} catch (SQLException e) {
			
			conn.rollback();
			e.printStackTrace();
		}
        
		
		
		return createdBorrower;
		
		
	}
	
	

	
	
	@Override
    public void update(Borrower borrower) throws SQLException {
		
		
		try {
			

	
        PreparedStatement prepareStatement = null;
        
        String sql = "UPDATE " + table + " SET name = ?, address = ?, phone = ? WHERE cardNo = ?;";
        
        prepareStatement = conn.prepareStatement(sql);
        
        prepareStatement.setString(1, borrower.getName());
        prepareStatement.setString(2, borrower.getAddress());
        
        prepareStatement.setString(3, borrower.getPhone());
        prepareStatement.setInt(4, borrower.getCardNo());
        
        prepareStatement.executeUpdate();
        
        conn.commit();
        
		} catch (SQLException e) {
			
			conn.rollback();
			e.printStackTrace();
		}
        
    }

	
	
	@Override
    public void delete(Borrower borrower) throws SQLException {
		
		try {
			

	
         PreparedStatement prepareStatement = null;
         
         String sql = "DELETE FROM " + table + " WHERE cardNo = ?;";
         
         prepareStatement = conn.prepareStatement(sql);
         prepareStatement.setInt(1, borrower.getCardNo());
         
         prepareStatement.executeUpdate();
         conn.commit();
         
		} catch (SQLException e) {
			
			conn.rollback();
			e.printStackTrace();
			
		}
  }
	
	
	
	@Override
	public Borrower get(int id) throws SQLException {
		
		
	    PreparedStatement prepareStatement = null;
	    ResultSet resultSet = null;
	    
	    String sql = "SELECT * FROM " + table + " WHERE cardNo = ?;";
	    
	    prepareStatement = conn.prepareStatement(sql);
	    prepareStatement.setInt(1, id);
	    resultSet = prepareStatement.executeQuery();
	    
	   Borrower borrowerfound = null;
	    
	      if(resultSet.next()) {
	    	  borrowerfound = new Borrower(resultSet.getInt("cardNo"), resultSet.getString("name"),
	            		 resultSet.getString("address"),resultSet.getString("phone"));
	         }
	      
	    return borrowerfound;
	  }

	
	
	@Override
    public List<Borrower> getAll() throws SQLException {
		
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        
        List<Borrower> returnList = new ArrayList<>();
        
          String sql = "SELECT * FROM " + table + ";";
         
        
        prepareStatement = conn.prepareStatement(sql);
        resultSet = prepareStatement.executeQuery();
        
        while (resultSet.next()) {
        	
            returnList.add(new Borrower(resultSet.getInt("cardNo"), resultSet.getString("name"),
            		resultSet.getString("address"),resultSet.getString("phone")));
        }
        
        return returnList;
    }
}
