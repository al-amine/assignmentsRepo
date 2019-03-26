package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import bean.Book;


public class BookDao implements Dao<Book> {
	

	private static String separator = "-";
	private static String filepath = "./resources/books";

	// creates new Publisher in the file
	
	@Override
	public void create(Book book) throws IOException {
		
		// WARNING this does not account for if there is already another entry with the same id!!
		
		String newEntry = book.getId() + separator + book.getTitle()+ separator + book.getAuthorId()+ separator +book.getPublisherId();
		FileWriter fw = new FileWriter(filepath, true);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw);
		pw.println(newEntry);
		pw.close();
	}

	// deletes associated book entry using the bookId and returns the entry in an dataObject
	// returns null if it cannot find the publisher
	
	@Override
	public Book delete(int id) throws FileNotFoundException, IOException {
		
		// WARNING This does not account for dependencies with Book
		
		FileReader fr = new FileReader(filepath);
		BufferedReader br = new BufferedReader(fr);
		String currentLine;
		List<String> newFileString = new ArrayList<String>();
		Book deletedBook = null;
		
		// save all non deleted entries into newFileString
		
		while((currentLine = br.readLine()) != null) {
			String[] splitEntry = currentLine.split(separator);
			int entryId = Integer.parseInt(splitEntry[0]);

			if(entryId != id) {
				newFileString.add(currentLine);
			} else {
				deletedBook = new Book(entryId, splitEntry[1],Integer.parseInt(splitEntry[2]),Integer.parseInt(splitEntry[3]));
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
		
		return deletedBook;
	}

	@Override
	public void update(Book book) throws FileNotFoundException, IOException {
		
		// WARNING This does not account for if the id does not exist
		
		FileReader fr = new FileReader(filepath);
		BufferedReader br = new BufferedReader(fr);

		List<String> newEntries = new ArrayList<String>();

		String currentLine;
		while((currentLine = br.readLine()) != null) {
			String[] splitArray = currentLine.split(separator);
			int entryId = Integer.parseInt(splitArray[0]);

			if(entryId == book.getId()) {
				String newEntry = book.getId() + separator + book.getTitle()+ separator + book.getAuthorId()+ separator +book.getPublisherId();
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

	// returns the publisher you are looking for using the Id
	
	@Override
	public Book find(int id) throws FileNotFoundException, IOException {
		
		
		Book correctPublisher = null;
		FileReader fr = new FileReader(filepath);
		BufferedReader br = new BufferedReader(fr);
		String currentLine;
		while((currentLine = br.readLine()) != null) {
			String[] splitEntry = currentLine.split(separator);
			int entryId = Integer.parseInt(splitEntry[0]);
			if(entryId == id) {
				correctPublisher = new Book(entryId, splitEntry[1],Integer.parseInt(splitEntry[2]),Integer.parseInt(splitEntry[3]));
			}
		}
		br.close();
		return correctPublisher;
	}
	
	
	 // return the list of all the publishers 
	
	@Override
	
	public List<Book> findAll() throws FileNotFoundException, IOException {
		List<Book> books = new ArrayList<Book>();
		FileReader fr = new FileReader(filepath);
		BufferedReader br = new BufferedReader(fr);
		String currentLine;
		while((currentLine = br.readLine()) != null) {
			String[] splitEntry = currentLine.split(separator);
			Book book = new Book(Integer.parseInt(splitEntry[0]), splitEntry[1],Integer.parseInt(splitEntry[2]),Integer.parseInt(splitEntry[3]));
			books.add(book);
		}

		br.close();

		return books;
	}
	



	
	
}
