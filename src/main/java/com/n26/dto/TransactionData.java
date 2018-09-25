/**
 * 
 */
package com.n26.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * @author natanwar
 *
 */
public class TransactionData {
	private BigDecimal amount;
	private ZonedDateTime dateTime;
	
	public TransactionData() {
	}
	public TransactionData(BigDecimal amount, ZonedDateTime dateTime) {
		this.amount = amount;
		this.dateTime = dateTime;
	}

	public TransactionData(TransactionData t) {
		this.amount = t.amount;
		this.dateTime = t.dateTime;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal parsedAmount) {
		this.amount = parsedAmount;
	}

	public ZonedDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(ZonedDateTime dateTime) {
		this.dateTime = dateTime;
	}

}
