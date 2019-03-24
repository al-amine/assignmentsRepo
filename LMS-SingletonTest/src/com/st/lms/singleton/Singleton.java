package com.st.lms.singleton;

public class Singleton {

	private static Singleton sing;
    
    static{
    	sing = new Singleton();
    }
     
    private Singleton(){
     
    }
     
    public static Singleton getInstance(){
        return sing;
    }
     
    
    
    public void test(){
        System.out.println(" it is working ");
    }
    
    
   
	
	public static void main(String[] args) {
		Singleton ms = getInstance();
        ms.test();
	}
	

}
