/**
 * 
 */
package com.n26.dto;

/**
 * @author natanwar
 *
 */
public class TransactionRequest {

	private String amount;
	private String timestamp;

	public TransactionRequest() {

	}

	public TransactionRequest(String amount, String timestamp) {
		this.amount = amount;
		this.timestamp = timestamp;
	}

	public TransactionRequest(TransactionRequest transactionRequest) {
		this.amount = transactionRequest.amount;
		this.timestamp = transactionRequest.timestamp;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
