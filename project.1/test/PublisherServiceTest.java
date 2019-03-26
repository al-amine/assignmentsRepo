import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import service.PublisherService;


public class PublisherServiceTest {

	
	
    public PublisherServiceTest() {
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
    public void testCreate() throws IOException {
  		
    System.out.println("CreatePublisher");
    int id = 35;
    String name ="Jean Saint Martin";
    String address ="app 75 Imm 10 groupe 16 Sait Christof Ave Paris,France";
    String phone ="+33 677 285 4579";
    
    PublisherService instance = new PublisherService();
    
    int expResult = 1;
    int result = instance.createPublisher(id, name,address,phone);

    assertEquals(expResult, result);
    
    // TODO review the generated test code and remove the default call to fail.
  }
  	
  	
  @Test
  public void testupdate() throws FileNotFoundException, IOException {
  	
     System.out.println("updatePublisher");
     int id = 23;
     String newname = "Alex Seushard";
     String newaddress ="1128 East 15th St Casper Wy,US";
     String newphone ="+1 307 248 1312";
     
     PublisherService instance = new PublisherService();
     
     int expResult = 1;
     int result = instance.updatePublisher(id,newname,newaddress,newphone);
     assertEquals(expResult, result);
     
     // TODO review the generated test code and remove the default call to fail.
     
  }
    
    
    
@Test
public void testDelete() throws IOException {
		
System.out.println("DeletePublisher");
int id = 35;
PublisherService instance = new PublisherService();

int expResult = 1;
int result = instance.deletePublisher(id);

assertEquals(expResult, result);

// TODO review the generated test code and remove the default call to fail.
}
	
    
    @Test
    public void testShowPublishersAndBooks() throws IOException {
    		
    System.out.println("ShowPublishersAndBooks");
    PublisherService instance = new PublisherService();

    int expResult = 1;
    int result = instance.showPublisherAndBooks();

    assertEquals(expResult, result);

    // TODO review the generated test code and remove the default call to fail.
    }
    
}
