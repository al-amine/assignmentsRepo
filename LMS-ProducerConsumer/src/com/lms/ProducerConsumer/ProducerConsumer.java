package com.lms.ProducerConsumer;

public class ProducerConsumer  { 
	
	
	
    public static void main(String[] args) throws InterruptedException { 
    	

        final ProduceConsume p = new ProduceConsume(); 
       
        Thread t1 = new Thread(new Runnable() 
        { 
            @Override
            public void run() 
            { 
                try
                { 
                    p.produce(); 
                } 
                catch(InterruptedException e) 
                { 
                    e.printStackTrace(); 
                } 
            } 
        }); 
  

        Thread t2 = new Thread(new Runnable() 
        { 
            @Override
            public void run() 
            { 
                try
                { 
                    p.consume(); 
                } 
                catch(InterruptedException e) 
                { 
                    e.printStackTrace(); 
                } 
            } 
        }); 
  

        t1.start(); 
        t2.start(); 
  

        t1.join(); 
        t2.join(); 
    } 
  

 
} 
