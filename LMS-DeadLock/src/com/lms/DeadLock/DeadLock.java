package com.lms.DeadLock;

public class DeadLock {
	 
    String s = "Me";
    String p = "You";
     
    Thread trd1 = new Thread("Thread 1"){
    	
        public void run(){
            while(true){
            	
                    synchronized(s){
                    synchronized(p){
                    	
                        System.out.println(s + p);
                                   }
                                }
                       }
        }
    };
     
    Thread trd2 = new Thread("Thread 2"){
    	
        public void run(){
            while(true){
            	
                     synchronized(p){
                	
                     synchronized(s){
                     System.out.println(p + s);
                                  }
                               }
                        }
                }    
    };
     
    public static void main(String a[]){
    	DeadLock dl = new DeadLock();
    	
        dl.trd2.start();
        dl.trd1.start();
 
    }
}