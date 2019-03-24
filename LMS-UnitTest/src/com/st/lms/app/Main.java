package com.st.lms.app;

import java.io.IOException;

import com.st.lms.dao.AuthorDao;

public class Main {

	public static void main(String[] args) {
		
		
	   try {
		    AuthorDao.readAuthors();
		    AuthorDao.writeAuthors();
			System.out.println(AuthorDao.readAuthors());
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
