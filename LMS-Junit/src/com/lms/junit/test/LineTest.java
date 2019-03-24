package com.lms.junit.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.lms.junit.Line;


public class LineTest {
	
	
	
    public LineTest() {
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
    public void testDistance() {
        Line l = new Line(+5.0,+7.1,+2.3,2);
        assertEquals(+5.0, l.getDistance(), 1.0e-4);
        assertEquals(+5.0, l.getDistance(), 1.0e-4);
    }
    
    @Test
    public void testParallel() {
        Line l1 = new Line(4, 5, 6, 4 );
        Line l2 = new Line(7, 1, 2, 5 );
        assertTrue(l1.parallelTo(l2));


}
    
}