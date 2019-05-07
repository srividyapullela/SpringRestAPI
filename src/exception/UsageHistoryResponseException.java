package com.reliant.sm.exception;

public class UsageHistoryResponseException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UsageHistoryResponseException(String msg){
		super(msg);
	}
	
	public UsageHistoryResponseException() {
		   super();
	}
   
	
    
    /**
     * Instantiates a new dAO exception.
     *
     * @param throwable the throwable
     */
    public UsageHistoryResponseException(Throwable throwable) {
        super(throwable);

    }

    
    /**
     * Instantiates a new dAO exception.
     *
     * @param msg the msg
     * @param throwable the throwable
     */
    public UsageHistoryResponseException(String msg, Throwable throwable) {
 	   super(msg, throwable);
      
    }
	

}
