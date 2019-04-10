package com.lms.dao;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lms.model.Branch;
import com.lms.util.ConnectionPrep;

public class LibraryBranchDao implements Dao<Branch> {
	
	
	private static Connection conn = ConnectionPrep.getConnection();
	private final static String table = "tbl_library_branch";
	
	

	public LibraryBranchDao() throws SQLException, IOException{
		
	}
	
	
	public Branch create(String branchName, String branchAddress) throws SQLException {
		
		Branch createdBranch = null;
		
		
		try {
			
		
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        
        String sql = "INSERT INTO " + table + " (branchName, branchAddress)VALUES (?,?);";
        
        prepareStatement = conn.prepareStatement(sql);
        prepareStatement.setString(1, branchName);
        
        prepareStatement.setString(2, branchAddress);
        prepareStatement.executeUpdate();
        
        conn.commit();
        
        
        sql = "SELECT * FROM " + table + " ORDER BY branchId DESC LIMIT 1;";
        
        prepareStatement = conn.prepareStatement(sql);
        resultSet = prepareStatement.executeQuery();
        resultSet.next();
        
        createdBranch = new Branch(resultSet.getInt("branchId"), resultSet.getString("branchName"),resultSet.getString("branchAddress"));
        
		
		} catch (SQLException e) {
			
			conn.rollback();
			e.printStackTrace();
		}
		
		return createdBranch;

	}

	
	

	
	@Override
    public void update(Branch branch) throws SQLException {
		
		try {
			

	
        PreparedStatement prepareStatement = null;
        
        String sql = "UPDATE " + table + " SET branchName = ?, branchAddress = ? WHERE branchId = ?;";
        
        prepareStatement = conn.prepareStatement(sql);
        
        prepareStatement.setString(1, branch.getName());
        prepareStatement.setString(2, branch.getAddress());
        
        prepareStatement.setInt(3, branch.getId());

        
        prepareStatement.executeUpdate();
        
        conn.commit();
        
		} catch (SQLException e) {
			
			conn.rollback();
			e.printStackTrace();
		}
        
    }
	
	
	
	@Override
    public void delete(Branch branch) throws SQLException {
		
		try {
			
         PreparedStatement prepareStatement = null;
         
         String sql = "DELETE FROM " + table + " WHERE branchId = ?;";
         
         prepareStatement = conn.prepareStatement(sql);
         prepareStatement.setInt(1, branch.getId());
         
         prepareStatement.executeUpdate();
         
         conn.commit();
         
		} catch (SQLException e) {
			
			conn.rollback();
			e.printStackTrace();
		}
  }

	
	
	
	@Override
	public Branch get(int id) throws SQLException {
		
		
	    PreparedStatement prepareStatement = null;
	    ResultSet resultSet = null;
	    
	    String sql = "SELECT * FROM " + table + " WHERE branchId = ?;";
	    
	    prepareStatement = conn.prepareStatement(sql);
	    prepareStatement.setInt(1, id);
	    resultSet = prepareStatement.executeQuery();
	    
	   Branch branchfound = null;
	    
	      if(resultSet.next()) {
	             branchfound = new Branch(resultSet.getInt("branchId"), resultSet.getString("branchName"),
	            		 resultSet.getString("branchAddress"));
	         }
	      
	    return branchfound;
	  }
	
	

	@Override
    public List<Branch> getAll() throws SQLException {
		
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        
        List<Branch> returnList = new ArrayList<>();
        
          String sql = "SELECT * FROM " + table + ";";
         
        
        prepareStatement = conn.prepareStatement(sql);
        resultSet = prepareStatement.executeQuery();
        
        while (resultSet.next()) {
        	
            returnList.add(new Branch(resultSet.getInt("branchId"), resultSet.getString("branchName"),
            		resultSet.getString("branchAddress")));
        }
        
        return returnList;
    }
}
