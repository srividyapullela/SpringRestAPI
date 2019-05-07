package com.reliant.sm.exception;

/**
 * @author bbachin1
 * The DataAccessLayerException class used to handle application related exceptions.
 * 
 */

public class SmartMainDAOException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
   
	private String errorCode = "O";
    private String errorMessage;
    private String reason;
    
    /**
     * @param errorCode
     * @param reason
     * @param errorMessage
     */
    
    public SmartMainDAOException(String errorCode, String errorMsg, String reason) {
    	this.errorCode = errorCode;
    	this.reason = reason;
    	this.errorMessage = errorMsg;
    	
    }

    
    public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
}
