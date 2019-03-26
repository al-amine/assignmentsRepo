package dao;

import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import bean.Author;

public class AuthorDao implements Dao<Author> {


		private static String separator = "-";
		private static String filepath = "./resources/authors";

		// creates new Author in the file
		
		@Override
		public void create(Author author) throws IOException{
			
			
			String entry = author.getId() + separator + author.getName();
			FileWriter fw = new FileWriter(filepath, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			pw.println(entry);
			pw.close();
		}

		// deletes associated book entry using the bookId and returns the entry in an dataObject
		// returns null if it cannot find the publisher
		
		@Override
		public Author delete(int id) throws FileNotFoundException, IOException {
			
			// WARNING This does not account for dependencies with Book
			
			FileReader fr = new FileReader(filepath);
			BufferedReader br = new BufferedReader(fr);
			String currentLine;
			List<String> newFileString = new ArrayList<String>();
			Author deletedAuthor = null;
			
			// save all non deleted entries into newFileString
			
			while((currentLine = br.readLine()) != null) {
				String[] splitEntry = currentLine.split(separator);
				int entryId = Integer.parseInt(splitEntry[0]);

				if(entryId != id) {
					newFileString.add(currentLine);
				} else {
					deletedAuthor = new Author(entryId, splitEntry[1]);
				}
			}
			br.close();
			

			FileWriter fw = new FileWriter(filepath, false);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			// put the entries back without the deleted entry
			for(String entry : newFileString) {
				pw.println(entry);
				
			}
			pw.close();
			
			// return null if it did not find a match
			return deletedAuthor;
		}

		// Not sure about this (maybe pass id and object and compare the object with the entry and if anything is different then update
		
		@Override
		public void update(Author author) throws FileNotFoundException, IOException {
			
			// WARNING This does not account for if the id does not exist
			
			FileReader fr = new FileReader(filepath);
			BufferedReader br = new BufferedReader(fr);
			
			List<String> newEntries = new ArrayList<String>();
			
			String currentLine;
			while((currentLine = br.readLine()) != null) {
				String[] splitArray = currentLine.split(separator);
				int entryId = Integer.parseInt(splitArray[0]);
				
				if(entryId == author.getId()) {
					String newEntry = author.getId() + separator + author.getName();
					newEntries.add(newEntry);
				} else {
					newEntries.add(currentLine);
				}
			}
			br.close();
			
			FileWriter fw = new FileWriter(filepath, false);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			for(String entry : newEntries) {
				pw.println(entry);
			}
			pw.close();
			
		}
		
		// returns the author you are looking for using his Id
		
		@Override
		public Author find(int id) throws FileNotFoundException, IOException {
			
			Author correctAuthor = null;
			FileReader fr = new FileReader(filepath);
			BufferedReader br = new BufferedReader(fr);
			String currentLine;
			while((currentLine = br.readLine()) != null) {
				String[] splitEntry = currentLine.split(separator);
				int entryId = Integer.parseInt(splitEntry[0]);
				if(entryId == id) {
					correctAuthor = new Author(entryId, splitEntry[1]);
				}
			}
			br.close();
			return correctAuthor;
		}

		
		// return the list of all the authors 
		
		@Override
		public List<Author> findAll() throws FileNotFoundException, IOException {
			
			List<Author> authors = new ArrayList<Author>();
			FileReader fr = new FileReader(filepath);
			BufferedReader br = new BufferedReader(fr);
			String currentLine;
			while((currentLine = br.readLine()) != null) {
				String[] splitEntry = currentLine.split(separator);
				Author author = new Author(Integer.parseInt(splitEntry[0]), splitEntry[1]);
				authors.add(author);
			}
			
			br.close();
			
			return authors;
		}
		
		
	}
	
	
	
	


