/**
 * 
 */
package com.n26.dto.builder;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import com.n26.dto.TransactionData;

/**
 * @author natanwar
 *
 */
public class TransactionDataBuilder {

	private TransactionData trn;

	public TransactionDataBuilder() {
		trn = new TransactionData();
	}

	public TransactionDataBuilder withAmount(BigDecimal amount) {
		trn.setAmount(amount);
		return this;
	}

	public TransactionDataBuilder withTimeStamp(ZonedDateTime timestamp) {
		trn.setDateTime(timestamp);
		return this;
	}

	public TransactionData build() {
		return trn;
	}
}
