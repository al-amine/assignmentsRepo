package com.st.lms.singleton;

public class SingletonEag {
	


	    private static volatile SingletonEag sSoleInstance = new SingletonEag();

	    //private constructor.
	    private SingletonEag(){}

	    public static SingletonEag getInstance() {
	        return sSoleInstance;
	    }


}
