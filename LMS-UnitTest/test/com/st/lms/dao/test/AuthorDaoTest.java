package com.st.lms.dao.test;



import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;


import org.junit.Before;
import org.junit.Test;

import com.st.lms.dao.AuthorDao;
import com.st.lms.model.Author;

public class AuthorDaoTest {
	
	
	
	private List<Author> authors;
	
	@Before
	public void readFile() throws IOException {
		
		authors = AuthorDao.readAuthors();
		
	}
	
	
	@Test
	public void readFileNumAuthor() {
		
		assertTrue(authors.size() > 0);
		

	}

	
	@Test
	public void readFileAnotherTest() {
		
		
		assertTrue(authors.get(0).getBooks().size() > 0);
		
		
		
	}
	
	@Test
	public void writeFileAnotherTest() throws IOException {
		
		
		List<Author> authorscheck = null;
		AuthorDao.writeAuthors();
		authorscheck = AuthorDao.readAuthors();
		
	
		
		assertTrue(authors.size() < authorscheck.size());
		
		
		
	}
	
}
