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
import bean.Publisher;

public class PublisherDao  implements Dao<Publisher>{


		private static String separator = "-";
		private static String filepath = "./resources/publishers";

		// creates new Publisher in the file
		
		@Override
		public void create(Publisher publisher) throws IOException {
			// WARNING this does not account for if there is already another entry with the same id!!
			String newEntry = publisher.getId() + separator + publisher.getName() + 
					separator + publisher.getAddress() + separator + publisher.getPhone();
			FileWriter fw = new FileWriter(filepath, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			pw.println(newEntry);
			pw.close();
		}

		// deletes associated book entry using the bookId and returns the entry in an dataObject
		// returns null if it cannot find the publisher
		
		@Override
		public Publisher delete(int id) throws FileNotFoundException, IOException {
			// WARNING This does not account for dependencies with Book
			FileReader fr = new FileReader(filepath);
			BufferedReader br = new BufferedReader(fr);
			String currentLine;
			List<String> newFileString = new ArrayList<String>();
			Publisher deletedPublisher = null;
			// save all non deleted entries into newFileString
			while((currentLine = br.readLine()) != null) {
				String[] splitEntry = currentLine.split(separator);
				int entryId = Integer.parseInt(splitEntry[0]);

				if(entryId != id) {
					newFileString.add(currentLine);
				} else {
					deletedPublisher = new Publisher(entryId, splitEntry[1], splitEntry[2], splitEntry[3]);
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
			
			return deletedPublisher;
		}

		@Override
		public void update(Publisher publisher) throws FileNotFoundException, IOException {
			
			// WARNING This does not account for if the id does not exist
			
			FileReader fr = new FileReader(filepath);
			BufferedReader br = new BufferedReader(fr);

			List<String> newEntries = new ArrayList<String>();

			String currentLine;
			while((currentLine = br.readLine()) != null) {
				String[] splitArray = currentLine.split(separator);
				int entryId = Integer.parseInt(splitArray[0]);

				if(entryId == publisher.getId()) {
					String newEntry = publisher.getId() + separator + publisher.getName() + separator + publisher.getAddress() + separator + publisher.getPhone();
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
		public Publisher find(int id) throws FileNotFoundException, IOException {
			
			
			Publisher correctPublisher = null;
			FileReader fr = new FileReader(filepath);
			BufferedReader br = new BufferedReader(fr);
			String currentLine;
			while((currentLine = br.readLine()) != null) {
				String[] splitEntry = currentLine.split(separator);
				int entryId = Integer.parseInt(splitEntry[0]);
				if(entryId == id) {
					correctPublisher = new Publisher(entryId, splitEntry[1],  splitEntry[2], splitEntry[3]);
				}
			}
			br.close();
			return correctPublisher;
		}
		
		
		 // return the list of all the publishers 
		
		@Override
		public List<Publisher> findAll() throws FileNotFoundException, IOException {
			List<Publisher> publishers = new ArrayList<Publisher>();
			FileReader fr = new FileReader(filepath);
			BufferedReader br = new BufferedReader(fr);
			String currentLine;
			while((currentLine = br.readLine()) != null) {
				String[] splitEntry = currentLine.split(separator);
				Publisher publisher = new Publisher(Integer.parseInt(splitEntry[0]), splitEntry[1], splitEntry[2], splitEntry[3]);
				publishers.add(publisher);
			}

			br.close();

			return publishers;
		}


	
	
}
