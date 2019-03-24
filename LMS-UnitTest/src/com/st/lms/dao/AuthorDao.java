package com.st.lms.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.nio.file.Files;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.st.lms.model.Author;
import com.st.lms.model.Book;

   public class AuthorDao {
	
	public static List<Author> readAuthors() throws IOException {
		
		FileInputStream fin = new FileInputStream("./resources/author");
		BufferedReader buffReader = new BufferedReader(new InputStreamReader(fin));
		
		Map<String, List<Book>> authorBookMap = new HashMap<String, List<Book>>();
		List<Author> authors = new ArrayList<Author>();
		String authorLine;
		
		while((authorLine = buffReader.readLine()) != null) {
			String[] splitArray = authorLine.split(";");
			
			Book b = new Book();
			b.setTitle(splitArray[1]);
			b.setIsbn(splitArray[2]);
			
			if(authorBookMap.containsKey(splitArray[0])){
				authorBookMap.get(splitArray[0]).add(b);
			}
			else {
				List<Book> books = new ArrayList<>();
				books.add(b);
				authorBookMap.put(splitArray[0], books);
			}
			
		}
		
		for( Entry<String, List<Book>> entry : authorBookMap.entrySet() ) {
			Author author = new Author();
			author.setName(entry.getKey());
			author.setBooks(entry.getValue());
			authors.add(author);
		}
		
		
		buffReader.close();
		return authors;
	}
	
	
	
	public static void writeAuthors() throws IOException {
		
		
		
		
		  Path file = Paths.get("./resources/sheet");
		  String filename= "./resources/author";
		  
		
		  BufferedReader br = new BufferedReader(new InputStreamReader(Files.newInputStream(file)));
		  FileWriter w = new FileWriter(filename,true);
 
	         
		  
		  
		  
		  Map<String, List<Book>> authorBookMap = new HashMap<String, List<Book>>();
			List<Author> authors = new ArrayList<Author>();
			String authorLine;
			
			while((authorLine = br.readLine()) != null) {
				String[] splitArray = authorLine.split(";");
				
				
				Book b = new Book();
				b.setTitle(splitArray[1]);
				b.setIsbn(splitArray[2]);
				
				if(authorBookMap.containsKey(splitArray[0])){
					authorBookMap.get(splitArray[0]).add(b);
				}
				else {
					List<Book> books = new ArrayList<>();
					books.add(b);
					authorBookMap.put(splitArray[0], books);
				}
				
				w.write(authorLine+"\n");
				
				
				
			}
			
			
			
			
//		  
//          String line = null;
//          
//          
//          while((line = br.readLine()) != null){
//        	  
//        	 FileWriter w = new FileWriter(filename,true);
// 	         w.write(line);
// 	        
// 
//          }
//		  
//		  
			
			for( Entry<String, List<Book>> entry : authorBookMap.entrySet() ) {
				Author author = new Author();
				author.setName(entry.getKey());
				author.setBooks(entry.getValue());
				authors.add(author);
			}
		  
	          
	         br.close();
	         w.close();
		
		
	}
	
	

}
