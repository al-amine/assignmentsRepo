package com.lms.menu;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


import com.lms.model.Author;
import com.lms.model.Book;
import com.lms.model.Publisher;
import com.lms.service.AdministratorService;

/**
 * The text-based menu UI for administrators maintaining cataloging data.
 *
 */
public final class CatalogerMenu {
	/**
	 * The service class we use to handle database interfacing for us.
	 */
	private final AdministratorService service;
	/**
	 * The helper we use for interacting with the user.
	 */
	private final MenuHelper mh;

	/**
	 * To initialize the menu, the caller must provide I/O streams, the database
	 * connection, and the service class. Note that the caller is responsible for
	 * ensuring that all of these resources are properly closed after the menu goes
	 * out of scope.
	 *
	 * @param inStream     how to get input from the user
	 * @param outStream    how to send output to the user
	 * @param dbConnection how to connect to the database
	 * @param service      the service class to do the actions the user calls for
	 * @param menuHelper   the helper class to interact with the user
	 */
	public CatalogerMenu(final AdministratorService service,
			final MenuHelper menuHelper) {
		this.service = service;
		mh = menuHelper;
	}

	private Author addAuthor(){
		final String name = mh.getString("Name of new author:");
		final Optional<Author> existing = service.getAllAuthors().parallelStream()
				.filter(author -> name.equals(author.getName())).findAny();
		if (existing.isPresent()) {
			mh.printf("There is already an author named %s. ", name);
			if (!mh.inputBoolean("Add anyway? ")) {
				return existing.get();
			}
		}
		return service.createAuthor(name);
	}

	private Publisher addPublisher(){
		final String name = mh.getString("Name of new publisher:");
		final Optional<Publisher> existing = service.getAllPublishers().parallelStream()
				.filter(publisher -> name.equals(publisher.getName())).findAny();
		if (existing.isPresent()) {
			mh.printf("There is already a publisher named %s. ", name);
			if (!mh.inputBoolean("Add anyway? ")) {
				return existing.get();
			}
		}
		final String address = mh.getString("Address of new publisher:");
		final String phone = mh.getString("Phone Number of new publisher:");
		return service.createPublisher(name, address, phone);
	}

	private Optional<Author> chooseOrEnterAuthor(final String prompt){
		final List<Author> authors = service.getAllAuthors();
		final Optional<Optional<Author>> chosenAuthor = mh.chooseFromList(authors, prompt,
				"(Any other number to cancel.)\n" + prompt, "Add New", "No authors known",
				"No such author", Author::getName);
		if (chosenAuthor.isPresent()) {
			if (chosenAuthor.get().isPresent()) {
				return chosenAuthor.get();
			} else { // "Add new" chosen
				return Optional.of(addAuthor());
			}
		} else if (authors.isEmpty()) {
			return Optional.of(addAuthor());
		} else { // input out of range, i.e. cancel
			return Optional.empty();
		}
	}
	private Optional<Publisher> chooseOrEnterPublisher(final String prompt){
		final List<Publisher> publishers = service.getAllPublishers();
		final Optional<Optional<Publisher>> chosenPublisher = mh.chooseFromList(
				publishers, prompt, "(Any other number to cancel.)\n" + prompt, "Add New",
				"No publishers known", "No such publisher", Publisher::getName);
		if (chosenPublisher.isPresent()) {
			if (chosenPublisher.get().isPresent()) {
				return chosenPublisher.get();
			} else {
				return Optional.of(addPublisher());
			}
		} else if (publishers.isEmpty()) {
			return Optional.of(addPublisher());
		} else {
			return Optional.empty();
		}
	}
	private void addBook(){
		final Optional<Author> author = chooseOrEnterAuthor("Author of new book:");
		if (!author.isPresent()) {
			return;
		}
		final Optional<Publisher> publisher = chooseOrEnterPublisher(
				"Publisher of new book:");
		if (!publisher.isPresent()) {
			return;
		}
		final String title = mh.getString("Title of new book:");
		service.createBook(title, author.get(), publisher.get());
	}

	private void updateAuthor(){
		final Optional<Optional<Author>> chosenAuthor = mh.chooseFromList(
				service.getAllAuthors(), "Author to update:", "Author to update:",
				"Quit to previous menu", "No authors known", "No such author",
				Author::getName);
		Author author;
		if (chosenAuthor.isPresent()) {
			if (chosenAuthor.get().isPresent()) {
				author = chosenAuthor.get().get();
			} else { // "Quit" chosen
				return;
			}
		} else { // no authors or input out of range
			return;
		}
		final String name = mh.getString("Author's new name (blank to leave unchanged):");
		if (!name.isEmpty()) {
			author.setName(name);
		}
		service.updateAuthor(author);
	}

	private void updatePublisher(){
		final Optional<Optional<Publisher>> chosenPublisher = mh.chooseFromList(
				service.getAllPublishers(), "Publisher to update:",
				"Publisher to update:", "Quit to previous menu", "No publishers known",
				"No such publisher", Publisher::getName);
		final Publisher publisher;
		if (chosenPublisher.isPresent()) {
			if (chosenPublisher.get().isPresent()) {
				publisher = chosenPublisher.get().get();
			} else { // "Quit"
				return;
			}
		} else { // empty list or input out of range
			return;
		}
		mh.println("Enter publisher's new information (blank to leave field unchanged):");
		final String name = mh.getString("Name:");
		if (!name.isEmpty()) {
			publisher.setName(name);
		}
		final String address = mh.getString("Address:");
		if (!address.isEmpty()) {
			publisher.setAddress(address);
		}
		final String phone = mh.getString("Phone:");
		if (!phone.isEmpty()) {
			publisher.setPhone(phone);
		}
		service.updatePublisher(publisher);
	}

	private void updateBook(){
		final Optional<Optional<Book>> chosenBook = mh.chooseFromList(
				service.getAllBooks(), "Book to update:", "Book to update:",
				"Quit to previous menu", "No books known", "No such book",
				book -> String.format("%s by %s", book.getTitle(),
						Optional.ofNullable(book.getAuthor()).map(Author::getName)
								.orElse("an unknown author")));
	    Book book;
	    String titleup = null;
	    Author auth = null;
	    Publisher pub = null;
		if (chosenBook.isPresent()) {
			if (chosenBook.get().isPresent()) {
				book = chosenBook.get().get();
			} else { // "Quit"
				return;
			}
		} else { // none or input out of range
			return;
		}
		if (mh.inputBoolean("Change author of book?")) {

			final Optional<Author> author = chooseOrEnterAuthor("New author of book:");
			if (author.isPresent()) {
				auth = author.get();
			}
		}
		mh.printf("Currently published by %s. ", Optional.ofNullable(book.getPublisher())
				.map(Publisher::getName).orElse("an unknown publisher"));
		if (mh.inputBoolean("Change publisher of book?")) {
			final Optional<Publisher> publisher = chooseOrEnterPublisher(
					"New publisher of book:");
			if (publisher.isPresent()) {
				pub =publisher.get();
			}
		}
		final String title = mh.getString("New title (blank to leave unchanged):");
		if (!title.isEmpty()) {
			titleup = title;
		}
		
		Book updatedBook = new Book(book.getId(), titleup, auth, pub);
		service.updateBook(updatedBook);
	}

	private void deleteBook(){
		final Optional<Optional<Book>> chosenBook = mh.chooseFromList(
				service.getAllBooks(), "Choose book to remove:", "Book to remove:",
				"Quit to previous menu", "No books found", "No such book",
				book -> String.format("%s by %s", book.getTitle(),
						Optional.ofNullable(book.getAuthor()).map(Author::getName)
								.orElse("an unknown author")));
		if (chosenBook.isPresent() && chosenBook.get().isPresent()) {
			service.deleteBook(chosenBook.get().get());
		}
	}

	private void deleteAuthor(){
		final Optional<Optional<Author>> chosenAuthor = mh.chooseFromList(
				service.getAllAuthors(),
				"Choose author to remove with all his/her books:", "Author to remove:",
				"Quit to previous menu", "No authors found", "No such author",
				Author::getName);
		if (chosenAuthor.isPresent() && chosenAuthor.get().isPresent()) {
			service.deleteAuthor(chosenAuthor.get().get());
		}
	}

	private void deletePublisher(){
		final Optional<Optional<Publisher>> chosenPublisher = mh.chooseFromList(
				service.getAllPublishers(), "Choose author to remove with all their books:",
				"Publisher to remove:", "Quit to previous menu", "No publishers found",
				"No such publisher", Publisher::getName);
		if (chosenPublisher.isPresent() && chosenPublisher.get().isPresent()) {
			service.deletePublisher(chosenPublisher.get().get());
		}
	}
	/**
	 * Present a list of options to the user and dispatch handling the user's choice
	 * to the appropriate method until the user selects "quit".
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public void menu() {
		while (true) {
			mh.println("Would you like to:");
			mh.println("0) Quit to the previous menu");
			mh.println("1) Add a new author");
			mh.println("2) Add a new publisher");
			mh.println("3) Add a new book");
			mh.println("4) Update an author's name");
			mh.println("5) Update details of a publisher");
			mh.println("6) Update details of a book");
			mh.println("7) Delete a book");
			mh.println("8) Delete an author");
			mh.println("9) Delete a publisher");
			switch (mh.getString("Chosen action:")) {
			case "0":
				return;
			case "1":
				addAuthor();
				break;
			case "2":
				addPublisher();
				break;
			case "3":
				addBook();
				break;
			case "4":
				updateAuthor();
				break;
			case "5":
				updatePublisher();
				break;
			case "6":
				updateBook();
				break;
			case "7":
				deleteBook();
				break;
			case "8":
				deleteAuthor();
				break;
			case "9":
				deletePublisher();
				break;
			default:
				mh.println("Unknown action.");
				break;
			}
		}
	}
}