import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import bean.Publisher;
import dao.PublisherDao;

public class PublisherDaoTest {
	
	
	private Publisher publisher;
	private PublisherDao publisherDao;
	
    public PublisherDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
    
    @Test
  	public void createTest() throws FileNotFoundException, IOException {
      	
      	System.out.println("Create a Publisher");
      	
  		publisher = new Publisher(23, "Albert Foust","1128 East 15th St Casper Wy,US ","+1 307 248 1312");
  		publisherDao = new PublisherDao();
  		int cmp;
  		cmp = publisherDao.findAll().size();
  		publisherDao.create(publisher);
  		int postcmp = publisherDao.findAll().size();
  		assertTrue( postcmp > cmp);
  	}
    
    
  	
  	@Test
  	public void deleteTest() throws FileNotFoundException, IOException {
  		
  		System.out.println("Delete a Publisher");
  		
  		publisher = new Publisher(35, "Ibn Khaldoun"," app 205 Group 10 Saint Michelle Ave Paris,France ","+33 451 278 5963");
  		publisherDao = new PublisherDao();
  		
  		publisherDao.create(publisher);
  		int cmp = publisherDao.findAll().size();
  		publisherDao.delete(publisher.getId());
  		int postcmp = publisherDao.findAll().size();
  		assertTrue(cmp > postcmp);
  	}
    
    
    
      
  	
  	@Test
  	public void findAuthorTest() throws FileNotFoundException, IOException {
  		
  		System.out.println("Find a Publisher");
  		
  		publisher = new Publisher(35, "Ibn Khaldoun"," app 205 Group 10 Saint Michelle Ave Paris,France ","+33 451 278 5963");
  		publisherDao = new PublisherDao();
  		
  		publisherDao.create(publisher);
  		
  		assertEquals(publisher.getId(), publisherDao.find(publisher.getId()).getId());
  		
  	}
    
    

  	@Test
  	public void updateAuthorTest() throws FileNotFoundException, IOException {
  	
  		
  		System.out.println("Update a Publisher");
  		
  		publisherDao = new PublisherDao();
  		
  		Publisher author = publisherDao.find(35);
  		
  		Publisher newUpdate = new Publisher(35, "Jean De La Garçoniere"," app 205 Group 10 Saint Michelle Ave Paris,France ","+33 451 278 5963");
  		
  		publisherDao.update(newUpdate);
  		assertTrue(author.getName() != newUpdate.getName());
  		
  	}
    
    
    
    
    
}
