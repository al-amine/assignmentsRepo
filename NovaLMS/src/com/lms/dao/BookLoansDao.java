package com.lms.dao;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;


import com.lms.model.Book;
import com.lms.model.Borrower;
import com.lms.model.Branch;
import com.lms.model.Loan;
import com.lms.util.ConnectionPrep;



public class BookLoansDao {
	
	
	private static Connection conn = ConnectionPrep.getConnection();
	private final static String table = "tbl_book_loans";
	
	

	public BookLoansDao(){

	}
	
	
	
	public Loan create(Book book, Borrower borrower, Branch branch, LocalDateTime dateOut, LocalDateTime dueDate) throws SQLException, IOException {
		
		Loan createdLoan = null;
		
		try {
			

		
		BookDao bookDao = new BookDao();
		BorrowerDao borrowerDao = new BorrowerDao();
		LibraryBranchDao libraryBranchDao = new LibraryBranchDao();
		
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
		
        String sql = "INSERT INTO " + table + " (bookId, branchId, cardNo, dateOut, dueDate) VALUES (?,?,?,?,?);";
        
        prepareStatement = conn.prepareStatement(sql);
        prepareStatement.setInt(1, book.getId());
        prepareStatement.setInt(2, branch.getId());
        prepareStatement.setInt(3, borrower.getCardNo());
        prepareStatement.setDate(4, java.sql.Date.valueOf(dateOut.toLocalDate()));
        prepareStatement.setDate(5, java.sql.Date.valueOf(dueDate.toLocalDate()));
        prepareStatement.executeUpdate();
        
        conn.commit();
        
        sql = "SELECT * FROM " + table + " ORDER BY bookId,branchId,cardNo DESC LIMIT 1;";
        
        prepareStatement = conn.prepareStatement(sql);
        resultSet = prepareStatement.executeQuery();
        resultSet.next();
        
        Book b = bookDao.get(resultSet.getInt("bookId"));
        Borrower br = borrowerDao.get(resultSet.getInt("cardNo"));
        Branch ba = libraryBranchDao.get(resultSet.getInt("branchId"));
        
        
        LocalDateTime newDateOut = Instant.ofEpochMilli( resultSet.getDate("dateOut").getTime() )
        		.atZone( ZoneId.systemDefault() )
                .toLocalDateTime();
        LocalDateTime newDueDate = Instant.ofEpochMilli( resultSet.getDate("dueDate").getTime() )
                .atZone( ZoneId.systemDefault() )
                .toLocalDateTime();
        
         createdLoan = new Loan(b, br ,ba,newDateOut,newDueDate);
        
		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
			// TODO: handle exception
		}
        
        return createdLoan;
		
	}
	
	
	
	
	
	public void update(Loan loan) throws SQLException {
		
		
		try {
			

		
        PreparedStatement prepareStatement = null;
        
        String sql = "UPDATE " + table + " SET dateOut = ?, dueDate = ? WHERE bookId = ? AND branchId = ? AND cardNo = ?;";
        
        prepareStatement = conn.prepareStatement(sql);
        
        prepareStatement.setDate(1, java.sql.Date.valueOf( loan.getDateOut().toLocalDate()));
        
        prepareStatement.setDate(2, java.sql.Date.valueOf(loan.getDueDate().toLocalDate()));
        
        prepareStatement.setInt(3, loan.getBook().getId());

        prepareStatement.setInt(4, loan.getBranch().getId());
        
        prepareStatement.setInt(5, loan.getBorrower().getCardNo());
        
        prepareStatement.executeUpdate();
        
        conn.commit();
        
		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();

		}
		
	
		
		
	}
	
	
	public void delete(Loan loan) throws SQLException {
		
		try {
			

		
        PreparedStatement prepareStatement = null;
        
        String sql = "DELETE FROM " + table + " WHERE bookId = ? AND branchId = ? AND cardNo = ?;";
        
        prepareStatement = conn.prepareStatement(sql);
        prepareStatement.setInt(1, loan.getBook().getId());
        
        prepareStatement.setInt(2, loan.getBranch().getId());
        prepareStatement.setInt(3, loan.getBorrower().getCardNo());
        
        prepareStatement.executeUpdate();
        
        conn.commit();
        
		} catch (SQLException e) {
			
			conn.rollback();
			e.printStackTrace();
			// TODO: handle exception
		}
		
        
	}
	
	
	
	
	public Loan get(Book book, Borrower borrower, Branch branch) throws SQLException, IOException {
		
		BookDao bookDao = new BookDao();
		BorrowerDao borrowerDao = new BorrowerDao();
		LibraryBranchDao libraryBranchDao = new LibraryBranchDao();
		
		
	    PreparedStatement prepareStatement = null;
	    ResultSet resultSet = null;
	    
	    String sql = "SELECT * FROM " + table + " WHERE bookId = ? AND branchId = ? AND cardNo = ?;";
	    
	    prepareStatement = conn.prepareStatement(sql);
	    prepareStatement.setInt(1, book.getId());
	    prepareStatement.setInt(2, branch.getId());
	    prepareStatement.setInt(3, borrower.getCardNo());
	    
	    resultSet = prepareStatement.executeQuery();
	    
	   Loan loanfound = null;
	    
	      if(resultSet.next()) {

	          Book b = bookDao.get(resultSet.getInt("bookId"));
	          Borrower br = borrowerDao.get(resultSet.getInt("cardNo"));
	          Branch ba = libraryBranchDao.get(resultSet.getInt("branchId"));
	          
	          LocalDateTime foundDateOut = Instant.ofEpochMilli( resultSet.getDate("dateOut").getTime() )
	                  .atZone( ZoneId.systemDefault() )
	                  .toLocalDateTime();
	          LocalDateTime foundDueDate = Instant.ofEpochMilli( resultSet.getDate("dueDate").getTime() )
	                  .atZone( ZoneId.systemDefault() )
	                  .toLocalDateTime();
	    	  
	             loanfound = new Loan(b,br,ba,foundDateOut,foundDueDate);
	         }
	      
	    return loanfound;
		
	}
	
	
	public List<Loan> getAll() throws SQLException, IOException {
		
		BookDao bookDao = new BookDao();
		BorrowerDao borrowerDao = new BorrowerDao();
		LibraryBranchDao libraryBranchDao = new LibraryBranchDao();
		
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        
        List<Loan> returnList = new ArrayList<>();
        
          String sql = "SELECT * FROM " + table + ";";
         
        
        prepareStatement = conn.prepareStatement(sql);
        resultSet = prepareStatement.executeQuery();
        
        while (resultSet.next()) {
        	
	          Book b = bookDao.get(resultSet.getInt("bookId"));
	          Borrower br = borrowerDao.get(resultSet.getInt("cardNo"));
	          Branch ba = libraryBranchDao.get(resultSet.getInt("branchId"));
	          
	          
	          LocalDateTime foundDateOut = Instant.ofEpochMilli( resultSet.getDate("dateOut").getTime() )
	                  .atZone( ZoneId.systemDefault() )
	                  .toLocalDateTime();
	          LocalDateTime foundDueDate = Instant.ofEpochMilli( resultSet.getDate("dueDate").getTime() )
	                  .atZone( ZoneId.systemDefault() )
	                  .toLocalDateTime();
	    	  
        	
            returnList.add(new Loan(b,br,ba,foundDateOut,foundDueDate));
        }
        
        return returnList;
		
		
		

	}
	
	
	
}
