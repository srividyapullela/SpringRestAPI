package com.reliant.sm.exception;

/**
 * @author bbachin1
 * The UsageHistoryRequestException class used to handle REQUEST BASED EXCEPTIONS.
 * 
 */

public class UsageHistoryRequestException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UsageHistoryRequestException(String msg){
		super(msg);
	}
	
	public UsageHistoryRequestException() {
		   super();
	}
   
	
    
    /**
     * Instantiates a new dAO exception.
     *
     * @param throwable the throwable
     */
    public UsageHistoryRequestException(Throwable throwable) {
        super(throwable);

    }

    
    /**
     * Instantiates a new dAO exception.
     *
     * @param msg the msg
     * @param throwable the throwable
     */
    public UsageHistoryRequestException(String msg, Throwable throwable) {
 	   super(msg, throwable);
      
    }
	

}
